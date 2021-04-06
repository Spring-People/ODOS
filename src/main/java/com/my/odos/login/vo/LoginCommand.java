package com.my.odos.login.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {

    private String email;
    private String password;
    private boolean rememberEmail;

    public boolean isRememberEmail() {
        return rememberEmail;
    }

}
