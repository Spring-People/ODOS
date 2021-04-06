package com.my.odos.login.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfo {

    private int id;
    private String email;
    private String name;

    public AuthInfo(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

}
