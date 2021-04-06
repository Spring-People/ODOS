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

    //연결된 짝이 있는지 반환한다.
    public boolean isConnetcedMember(String email) {
        Member member = authRepository.findByEmail(email);

        return member.isConnected();
    }

}
