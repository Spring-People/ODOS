package com.my.odos;

import com.my.odos.domain.Member;
import com.my.odos.domain.Team;
import com.my.odos.member.repository.MemberRepository;
import com.my.odos.team.respository.TeamRepository;
import com.my.odos.login.vo.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @RequestMapping(value = "/main")
    public String main(HttpSession session, Model model) {

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();

        // 버그 수정: 그룹 id를 찾도록 변경
        Member loginMember = memberRepository.findById(currentId).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 없음")
        );

        int teamId = loginMember.getGroupId();

        Team team = teamRepository.findById(teamId);
        //메인 페이지에 들어온다는것은 그룹이 만들어졌다는 뜻
        model.addAttribute("uploadTime",team.getUploadTime());

        return "main/main";
    }
}
