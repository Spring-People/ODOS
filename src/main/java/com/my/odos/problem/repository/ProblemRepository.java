package com.my.odos.problem.repository;

import com.my.odos.domain.Problem;
import com.my.odos.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository <Problem, Integer> {
    List<Problem> findAllBy();
    List<Problem> findByGroupId(int group_id);
    Problem findById(int id);
    List<Problem> findAllByGroupIdOrderByIdDesc(int groupId);
}
