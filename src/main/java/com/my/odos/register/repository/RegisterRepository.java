package com.my.odos.register.repository;

import com.my.odos.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository <Member,Integer> {

    Member findByEmail(String email);
}
