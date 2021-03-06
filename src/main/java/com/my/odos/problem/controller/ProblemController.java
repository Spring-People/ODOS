package com.my.odos.problem.controller;

import com.my.odos.domain.Comment;
import com.my.odos.domain.Member;
import com.my.odos.domain.Problem;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.problem.repository.ProblemRepository;
import com.my.odos.problem.repository.UserRepository;
import com.my.odos.problem.service.CommentService;
import com.my.odos.problem.service.ProblemService;
import com.my.odos.problem.vo.SolvedRequest;
import com.my.odos.team.vo.TeamUploadTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final ProblemService problemService;
    private final CommentService commentService;

    @GetMapping("/problemlist")
    public String problemlist(HttpSession session, Model model){

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member user = userRepository.findById(currentId);
        List<Problem> problemList = problemRepository.findByGroupId(user.getGroupId());
        model.addAttribute("problemList",problemList);

        return "problem/problemlist";
    }

    @RequestMapping("problem/detail/{id}")
    public String problemdetail(@PathVariable int id, Model model, HttpSession session) throws Exception {

        Problem problem = problemService.findProblem(session, id);
        model.addAttribute("problem", problem);
        List<Comment> commentList = commentService.findCommentList(id);
        model.addAttribute("commentList", commentList);

        return "problem/problemdetail";
    }

    @GetMapping("/api/getProblems")
    @ResponseBody
    public List<Problem> getProblems(HttpSession session, HttpServletResponse response) {
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member user = userRepository.findById(currentId);
        int groupId = user.getGroupId();

        List<Problem> problemList = problemRepository.findAllByGroupId(groupId);

        return problemList;
    }

    @PutMapping("/api/problem/solved/{id}")
    @ResponseBody
    public int updateSolved(@PathVariable int id, @RequestBody SolvedRequest request){

        return problemService.updateSolved(id,request.getCheck_number());
    }
}
