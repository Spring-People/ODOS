package com.my.odos.invitation.controller;

import com.my.odos.domain.Invitation;
import com.my.odos.domain.Member;
import com.my.odos.exception.InvitationException;
import com.my.odos.invitation.repository.MemberRepository;
import com.my.odos.invitation.service.InvitationService;
import com.my.odos.invitation.vo.InvitaionEmailRequest;
import com.my.odos.invitation.vo.InvitationTeamRequest;
import com.my.odos.invitation.vo.LoginMemberInfo;
import com.my.odos.login.vo.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;
    private final MemberRepository memberRepository;

    @GetMapping("/api/receiveLoginInfo")
    @ResponseBody
    public LoginMemberInfo getLoginInfo(HttpSession session) {
        /*
        * session에서 현재 로그인한 member의 id를 리턴
        * */
        AuthInfo currentUser = (AuthInfo) session.getAttribute("authInfo");
        String id = String.valueOf(currentUser.getId());
        System.out.println(id);

        LoginMemberInfo info = new LoginMemberInfo();
        info.setId(id);

        return info;
    }

    @GetMapping("/api/getInvitation")
    @ResponseBody
    public Member getInvitation(HttpSession session) {
        /*
        * 받은 초대장이 있는지 확인하고 있으면 보낸 member의 정보를 리턴
        *
        * */
        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        int fromId = -1;
        Invitation invitation = null;
        try {
            invitation = invitationService.findInvitation(currentId);
            fromId = invitation.getFromId();

        } catch (InvitationException e) {
            e.printStackTrace();
        }

        Member sender = memberRepository.findById(fromId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않음")
        );
        return sender;
    }

    @PostMapping("/api/sendInvitation")
    @ResponseBody   // json 형태로 반할 때 사용
    public Boolean createInvitation(@RequestBody InvitaionEmailRequest request, HttpSession session) {
        // 1. 전달 받은 email로 DB에서 검색해 id 값을 찾는다.
        // 2. Session 객체에서 현재 로그인한 id 값을 찾는다.
        // 3. Invitation 레코드를 한 개 생성한다.

        String to_email = request.getTo_email();
        Member member = memberRepository.findByEmail(to_email);
        int toId = member.getId();
        AuthInfo currentUser = (AuthInfo) session.getAttribute("authInfo");
        int fromId = currentUser.getId();

        boolean isSucess = false;

        try {
            isSucess = invitationService.createInvitation(fromId, toId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSucess;
    }

    @PostMapping("/api/receiveInvitation")
    @ResponseBody
    public Boolean makeTeam(@RequestBody InvitationTeamRequest request) {
        // 1. 전달 받은 id 로부터 DB에서 해당하는 초대장 선택
        // 2. 초대장의 connected를 true, id에 해당하는 member들도 true 전환

        int fromId = request.getFromId();
        int toId = request.getToId();
        return invitationService.makeTeam(toId, fromId);
    }

}
