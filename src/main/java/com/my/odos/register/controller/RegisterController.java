package com.my.odos.register.controller;

import com.my.odos.register.service.RegisterService;
import com.my.odos.register.vo.RegisterRequest;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    //약관 동의
    @RequestMapping("/register/step1")
    public String handleStep1(){
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(@RequestParam(value="agree", defaultValue = "false") Boolean agreeVal, Model model){

        //동의하지 않았을때
        if(!agreeVal) { return "register/step1"; }

        model.addAttribute("registerRequest",new RegisterRequest());

        return "register/step2";
    }

    @PostMapping("/register/step3")
    public String handleStep3(RegisterRequest regReq){

        //비밀번호 확인한게 다를때
        if(!regReq.isPasswordEqualToConfirmPassword()) { return "register/step2"; }

        try{
            registerService.regist(regReq);
            return "register/step3"; //가입 완료 창
        } catch (DuplicateMemberException ex) {
            return "register/step2"; //이미 있는 아이디일때
        }
    }
}
