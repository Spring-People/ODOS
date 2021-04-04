package com.my.odos;

import com.my.odos.domain.Team;
import com.my.odos.invitation.repository.TeamRepository;
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

    @RequestMapping(value = "/main")
    public String main(HttpSession session, Model model) {

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Team team = teamRepository.findById(currentId);
        //메인 페이지에 들어온다는것은 그룹이 만들어졌다는 뜻
        model.addAttribute("uploadTime",team.getUploadTime());

        return "main/main";
    }
}
