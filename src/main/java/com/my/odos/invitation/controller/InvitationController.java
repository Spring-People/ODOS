package com.my.odos.invitation.controller;

import com.my.odos.domain.Member;
import com.my.odos.invitation.service.InvitationService;
import com.my.odos.invitation.vo.InvitaionEmailRequest;
import com.my.odos.invitation.vo.InvitationTeamRequest;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;
    private final MemberRepository memberRepository;

    /*
     * sendInvitation: Invitation 레코드를 생성한다.
     * */
    @PostMapping("/api/sendInvitation")
    @ResponseBody   // json 형태로 반환할 때 사용
    public Boolean sendInvitation(@RequestBody InvitaionEmailRequest request, HttpSession session) {
        String receiverEmail = request.getTo_email();
        Member receiver = memberRepository.findByEmail(receiverEmail);
        int receiverId = receiver.getId();
        AuthInfo currentUser = (AuthInfo) session.getAttribute("authInfo");
        int senderId = currentUser.getId();

        boolean isInvitationCreated = false;

        try {
            isInvitationCreated = invitationService.createInvitation(senderId, receiverId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInvitationCreated;
    }

    /*
     * receiveInvitation: senderId 와 receiverId 로 팀을 만든다
     * */
    @PostMapping("/api/receiveInvitation")
    @ResponseBody
    public Boolean receiveInvitation(@RequestBody InvitationTeamRequest request) {
        int senderId = request.getFromId();
        int receiverId = request.getToId();
        return invitationService.makeTeam(receiverId, senderId);
    }

}
