package br.com.zupedu.gui.mercado_livre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Usuario{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Email
    private String login;
    @NotBlank @Length(min = 6)
    private String senha;
    @PastOrPresent
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, String senha) {
        //Faz a criptografia da senha.
        senha = new BCryptPasswordEncoder().encode(senha);
        this.login = login;
        this.senha = senha;
        this.instanteCriacao = LocalDateTime.now();
    }
}
