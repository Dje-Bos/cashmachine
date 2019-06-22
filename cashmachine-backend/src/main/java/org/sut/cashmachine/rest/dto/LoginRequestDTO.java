package org.sut.cashmachine.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class LoginRequestDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(@NotBlank @Email String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
