package com.my.odos.login.controller;

import com.my.odos.exception.WrongIdPasswordException;
import com.my.odos.login.service.AuthService;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.login.vo.LoginCommand;
import com.my.odos.register.vo.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String form(Model model){

        model.addAttribute("loginCommand",new LoginCommand());
        return "login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, HttpSession session){
        try{
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());

            session.setAttribute("authInfo", authInfo);

             /*
                1. 이메일로 DB에서 객체 조회
                2. 객체에서 connected 변수 상태 확인
                3. connected = false 일 경우 초대장 페이지로 이동
            */
            String email = loginCommand.getEmail();
            boolean isConnectedMember = authService.isConnetcedMember(email);
            if (!isConnectedMember) {
                System.out.println("초대장 페이지 이");
                return "redirect:invitation/Invitation.html";
//                return "invitation/Invitation";
            }


            return "login/loginSuccess";
        } catch (WrongIdPasswordException e){
            return "login/loginForm";
        }
    }

}
