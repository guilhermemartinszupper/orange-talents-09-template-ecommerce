package br.com.zupedu.gui.mercado_livre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "senha nao pode ser em branco");
        Assert.isTrue(senha.length()>=6,"senha tem que ter no mínimo 6 caracteres");
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
