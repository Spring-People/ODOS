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

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final MemberRepository memberRepository;

    private final TeamService teamService;

    /*
     * createInvitation: senderId와 receiverId로 Invitation record 생
     * */
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

    /*
     * findInvitation: 현재 로그인한 id가 받은 초대장 반환
     * */
    public List<Invitation> findInvitation(int currentId) {
        List<Invitation> invitationList = invitationRepository.findByToId(currentId);
        if (invitationList == null) throw new InvitationException("받은 초대장이 없음");
        return invitationList;
    }

    /*
     * makeTeam: receiverId와 senderId를 이용해 팀 생성
     * sender와 receiver의 connected 상태 변경
     * */
    @Transactional
    public Boolean makeTeam(int receiverId, int senderId) {
        Invitation invitation = invitationRepository.findByToIdAndFromId(receiverId, senderId);
        invitation.setConnected(true);

        Member sender = memberRepository.findById(senderId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 없음")
        );
        sender.setConnected(true);

        Member receiver = memberRepository.findById(receiverId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 없음")
        );
        receiver.setConnected(true);

        teamService.makeTeam(sender, receiver);

        return true;
    }
}
