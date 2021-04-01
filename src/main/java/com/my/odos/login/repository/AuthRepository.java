package com.my.odos.login.repository;

import com.my.odos.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository <Member,Integer> {

    Member findByEmail(String email);
}
