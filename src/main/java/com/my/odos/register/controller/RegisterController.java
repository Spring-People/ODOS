package com.my.odos.register.controller;

import com.my.odos.register.service.RegisterService;
import com.my.odos.register.vo.RegisterRequest;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping("/register/step1")
    public String handleStep1(){
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(@RequestParam(value="agree", defaultValue = "false") Boolean agreeVal, Model model){

        if(!agreeVal){
            return "register/step1";
        }
        model.addAttribute("registerRequest",new RegisterRequest());
        return "register/step2";
    }

    @PostMapping("/register/step3")
    public String handleStep3(RegisterRequest regReq){

        if(regReq.isPasswordEqualToConfirmPassword() == false) {
            return "register/step2";
        }

        try{
            registerService.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            return "register/step2";
        }
    }
}
