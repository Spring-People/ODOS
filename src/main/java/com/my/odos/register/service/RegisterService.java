package com.my.odos.register.service;

import com.my.odos.domain.Member;
import com.my.odos.register.vo.RegisterRequest;
import com.my.odos.register.repository.RegisterRepository;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository registerRepository;

    public String regist(RegisterRequest req) throws DuplicateMemberException {

        Member check = registerRepository.findByEmail(req.getEmail());

        if(check != null){
            throw new DuplicateMemberException("dup email" + req.getEmail());
        }

        Member member = new Member();
        member.setEmail(req.getEmail());
        member.setName(req.getName());
        member.setConnected(false);
        member.setPw(req.getPassword());
        member.setGroup_id(1);

        registerRepository.save(member);

        return member.getName();
    }
}
