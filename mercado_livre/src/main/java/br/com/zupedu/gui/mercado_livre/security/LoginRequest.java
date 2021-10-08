package br.com.zupedu.gui.mercado_livre.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank @Email
    private String login;
    @NotBlank
    private String senha;

    public LoginRequest() {
    }

    public LoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.login,this.senha);
    }
}
