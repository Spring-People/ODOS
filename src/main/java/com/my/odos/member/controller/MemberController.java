package com.my.odos.member.controller;

import com.my.odos.domain.Invitation;
import com.my.odos.domain.Member;
import com.my.odos.exception.InvitationException;
import com.my.odos.invitation.service.InvitationService;
import com.my.odos.member.repository.MemberRepository;
import com.my.odos.login.vo.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final InvitationService invitationService;


    /*
     * getMemberInfo: 현재 로그인한 member 객체를 리턴한다.
     * */
    @GetMapping("/api/getMemberInfo")
    @ResponseBody
    public Member getMemberInfo(HttpSession session) {
        int currentLoginId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        return memberRepository.findById(currentLoginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 없음"));
    }

    /*
     * getSender: 현재 로그인한 id로 초대장을 보낸 멤버 중 가장 먼저 보낸 멤버 반환한다.
     * */
    @GetMapping("/api/getSender")
    @ResponseBody
    public Member getSender(HttpSession session) {
        int currentUserId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        int senderId = -1;
        List<Invitation> invitationList = null;
        try {
            invitationList = invitationService.findInvitation(currentUserId);
            senderId = invitationList.get(0).getFromId();

        } catch (InvitationException e) {
            e.printStackTrace();
        }

        return memberRepository.findById(senderId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않음")
        );
    }

}
