package br.com.zupedu.gui.mercado_livre.usuario;

import br.com.zupedu.gui.mercado_livre.validator.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class NovoUsuarioRequest {
    @NotBlank @Email @UniqueValue(domainClass = Usuario.class,nomeCampo = "login",message = "Não Foi possivel realizar cadastro " +
            "com esse email")
    private String login;
    @NotBlank @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }


    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login,new SenhaLimpa(this.senha));
    }
}
