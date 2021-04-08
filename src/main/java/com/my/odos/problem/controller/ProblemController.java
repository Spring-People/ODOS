package com.my.odos.problem.controller;

import com.my.odos.domain.Member;
import com.my.odos.domain.Problem;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.problem.repository.ProblemRepository;
import com.my.odos.problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;

    @GetMapping("/problemlist")
    public String problemlist(HttpSession session, Model model){

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member user = userRepository.findById(currentId);
        List<Problem> problemList = problemRepository.findByGroupId(user.getGroupId());
        model.addAttribute("problemList",problemList);

        return "problem/problemlist";
    }

    @GetMapping("/api/getProblems")
    @ResponseBody
    public List<Problem> getProblems(HttpSession session) {
        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member user = userRepository.findById(currentId);
        int groupId = user.getGroupId();

        List<Problem> problemList = problemRepository.findAllByGroupId(groupId);

        return problemList;
    }
}
