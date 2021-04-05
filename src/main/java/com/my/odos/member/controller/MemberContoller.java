package com.my.odos.member.controller;

import com.my.odos.domain.Member;
import com.my.odos.member.repository.MemberRepository;
import com.my.odos.login.vo.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberContoller {

    private final MemberRepository memberRepository;


    @GetMapping("/api/getMemberInfo")
    @ResponseBody
    public Member getMemberInfo(HttpSession session) {
        int currentUserId
                = ((AuthInfo) session.getAttribute("authInfo")).getId();
        System.out.println("currentUserId " + currentUserId);
        Member currentMember = memberRepository.findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 없음"));
        return currentMember;
    }

}
