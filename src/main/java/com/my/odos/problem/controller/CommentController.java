package com.my.odos.problem.controller;

import com.my.odos.domain.Comment;
import com.my.odos.domain.Member;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.problem.repository.UserRepository;
import com.my.odos.problem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

    @RequestMapping("/list")
    @ResponseBody
    private List<Comment> getCommentList(@RequestParam int problem_id, Model model) {
        return commentService.findCommentList(problem_id);
    }

    @RequestMapping("/insert")
    private String insertComment(@RequestParam int problem_id, @RequestParam String text, HttpSession session){

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member user = userRepository.findById(currentId);

        Comment comment = new Comment();
        comment.setCommentTime(new Date());
        comment.setName(user.getName());
        comment.setProblemId(problem_id);
        comment.setText(text);
        commentService.createComment(comment);

        return "redirect:/problem/detail/" + comment.getProblemId();
    }
}
