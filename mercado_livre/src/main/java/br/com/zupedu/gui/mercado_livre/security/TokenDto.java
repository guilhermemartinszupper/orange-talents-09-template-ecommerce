package br.com.zupedu.gui.mercado_livre.security;

public class TokenDto {

    private String token;
    private String autenticacao;

    public TokenDto(String token, String bearer) {
        this.token = token;
        autenticacao = bearer;
    }

    public String getToken() {
        return token;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    @Override
    public String toString() {
        return this.autenticacao + " " + token;
    }
}
