package com.my.odos.problem.service;

import com.my.odos.domain.Comment;
import com.my.odos.problem.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findCommentList(int problem_id){

        List<Comment> commentList = commentRepository.findByProblemId(problem_id);

        return commentList;
    }

    public int createComment(Comment comment) {

        commentRepository.save(comment);

        return comment.getProblemId();
    }
}
