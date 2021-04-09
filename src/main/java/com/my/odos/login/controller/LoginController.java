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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    @GetMapping
    public String form(Model model, @CookieValue(value="REMEMBER", required = false) Cookie rCookie){

        LoginCommand loginCommand = new LoginCommand();

        if(rCookie != null){
            loginCommand.setEmail(rCookie.getValue());
            loginCommand.setRememberEmail(true);
        }

        model.addAttribute("loginCommand",loginCommand);

        return "login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, HttpSession session, HttpServletResponse response){
        try{
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());

            session.setAttribute("authInfo", authInfo);

            Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");

            if(loginCommand.isRememberEmail()){
                rememberCookie.setMaxAge(60*60*24*30); //30일 동안 유지되는 쿠키 생성
            }else{
                rememberCookie.setMaxAge(0); //바로 삭제되는 쿠기 생성
            }
            response.addCookie(rememberCookie);

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
