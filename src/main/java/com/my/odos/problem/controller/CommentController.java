package com.my.odos.problem.controller;

import com.my.odos.domain.Comment;
import com.my.odos.domain.Member;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.problem.repository.CommentRepository;
import com.my.odos.problem.repository.UserRepository;
import com.my.odos.problem.service.CommentService;
import com.my.odos.problem.vo.CommentRequest;
import com.my.odos.problem.vo.ProblemIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private List<Comment> getCommentList(@RequestBody ProblemIdRequest request) {
        return commentService.findCommentList(request.getProblem_id());
    }

    @PostMapping("/insert")
    @ResponseBody //json 형태로 반환
    private Comment insertComment(@RequestBody CommentRequest request, HttpSession session){

        int currentId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member user = userRepository.findById(currentId);

        Comment comment = new Comment();
        comment.setCommentTime(new Date());
        comment.setName(user.getName());
        comment.setProblemId(request.getProblem_id());
        comment.setText(request.getText());
        commentService.createComment(comment);

        return comment;
    }
}
