package com.my.odos.register.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @Email
    private String email;
    @Size(min=6)
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String name;

    public boolean isPasswordEqualToConfirmPassword() {
        return password.equals(confirmPassword);
    }
}
