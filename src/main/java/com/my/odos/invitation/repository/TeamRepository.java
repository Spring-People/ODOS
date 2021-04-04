package com.my.odos.invitation.repository;

import com.my.odos.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findById(int id);
    List<Team> findAllBy();
}
