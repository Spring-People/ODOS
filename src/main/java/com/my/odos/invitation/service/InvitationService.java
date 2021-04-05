package com.my.odos.invitation.service;

import com.my.odos.domain.Invitation;
import com.my.odos.domain.Member;
import com.my.odos.exception.InvitationException;
import com.my.odos.invitation.repository.InvitationRepository;
import com.my.odos.member.repository.MemberRepository;
import com.my.odos.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final MemberRepository memberRepository;
    private final TeamService teamService;

    public Boolean createInvitation(int fromId, int toId) {
        Invitation check = invitationRepository.findByToIdAndFromId(toId, fromId);
        if (check != null) throw new InvitationException("해당 초대장이 이미 존재");

        Member memberCheck = memberRepository.findById(toId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 없음")
        );
        if (memberCheck.isConnected()) throw new InvitationException("해당 member는 이미 팀이 있음");

        Invitation invitation = new Invitation();
        invitation.setToId(toId);
        invitation.setFromId(fromId);
        invitation.setConnected(false);

        invitationRepository.save(invitation);
        return true;
    }

    public Invitation findInvitation(int currentId) {
        System.out.println("현재 접속한 id "+currentId);
        Invitation invitation = invitationRepository.findByToId(currentId);
        if (invitation == null) throw new InvitationException("받은 초대장이 없음");
        return invitation;
    }

    @Transactional
    public Boolean makeTeam(int toId, int fromId) {
        Invitation invitation = invitationRepository.findByToIdAndFromId(toId, fromId);
        invitation.setConnected(true);

        Member sender = memberRepository.findById(fromId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 없음")
        );
        sender.setConnected(true);

        Member receiver = memberRepository.findById(toId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 없음")
        );
        receiver.setConnected(true);

        teamService.makeTeam(sender, receiver);
        return true;
    }
}
