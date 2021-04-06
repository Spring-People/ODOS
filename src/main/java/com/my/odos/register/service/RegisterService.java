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

    //회원 등록
    public String regist(RegisterRequest req) throws DuplicateMemberException {

        Member check = registerRepository.findByEmail(req.getEmail());

        //이미 있는 아이디 일때
        if(check != null){
            throw new DuplicateMemberException("이미 등록된 email" + req.getEmail());
        }

        Member member = new Member();
        member.setEmail(req.getEmail());
        member.setName(req.getName());
        member.setConnected(false);
        member.setPw(req.getPassword());
        member.setGroupId(1); //처음 디폴트 그룹 ID를 1로 설정

        registerRepository.save(member);

        return member.getName();
    }
}
