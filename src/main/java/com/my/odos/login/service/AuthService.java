package com.my.odos.login.service;

import com.my.odos.domain.Member;
import com.my.odos.exception.WrongIdPasswordException;
import com.my.odos.login.repository.AuthRepository;
import com.my.odos.login.vo.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public AuthInfo authenticate(String email, String password){
        Member member = authRepository.findByEmail(email);

        if(member == null){
            throw new WrongIdPasswordException();
        }

        if(!member.matchPassword(password)){
            throw new WrongIdPasswordException();
        }

        return new AuthInfo(member.getId(),member.getEmail(),member.getName());
    }
}
