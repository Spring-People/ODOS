package com.my.odos.problem.repository;

import com.my.odos.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Integer> {

    List<Comment> findByProblemId(int problem_id);
}
