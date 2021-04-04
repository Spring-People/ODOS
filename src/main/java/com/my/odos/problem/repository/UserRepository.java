package com.my.odos.problem.repository;

import com.my.odos.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Integer> {
    Member findById(int id);
}
