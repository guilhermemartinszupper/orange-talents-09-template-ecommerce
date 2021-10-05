package br.com.zupedu.gui.mercado_livre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Email @Column(nullable = false,unique = true)
    private String login;
    @NotBlank @Length(min = 6) @Column(nullable = false)
    private String senha;
    @PastOrPresent @Column(nullable = false)
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, SenhaLimpa senha) {
        Assert.isTrue(StringUtils.hasLength(login),"email não pode ser em branco");
        Assert.notNull(senha,"senha nao pode ser nulo");
        this.login = login;
        this.senha = senha.hash();
        this.instanteCriacao = LocalDateTime.now();
    }


}
