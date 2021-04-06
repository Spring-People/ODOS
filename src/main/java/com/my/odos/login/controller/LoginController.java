package com.my.odos.login.controller;

import com.my.odos.exception.WrongIdPasswordException;
import com.my.odos.login.vo.LoginMemberInfo;
import com.my.odos.login.service.AuthService;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.login.vo.LoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

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
            String email = loginCommand.getEmail();
            boolean isConnectedMember = authService.isConnetcedMember(email);

            if (!isConnectedMember) {
                return "redirect:invitation/Invitation.html"; //초대장 페이지 이동
            }

            return "main/main";
        } catch (WrongIdPasswordException e){
            return "login/loginForm";
        }
    }

    @GetMapping("/api/receiveLoginInfo")
    @ResponseBody
    public LoginMemberInfo getLoginInfo(HttpSession session) {

        //session에서 현재 로그인한 member의 id를 리턴
        AuthInfo currentUser = (AuthInfo) session.getAttribute("authInfo");
        String id = String.valueOf(currentUser.getId());

        LoginMemberInfo info = new LoginMemberInfo();
        info.setId(id);

        return info;
    }

}
