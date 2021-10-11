package br.com.zupedu.gui.mercado_livre.produto.pergunta;

import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class Pergunta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false)
    private String titulo;
    @NotNull @Column(nullable = false)
    private final LocalDateTime instanteCriacao = LocalDateTime.now();
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Produto produto;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Usuario usuario;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        Assert.hasLength(titulo, "Titulo nao pode ser vazio");
        Assert.notNull(titulo, "Titulo é obrigatorio");
        Assert.notNull(produto, "Produto é Obrigatorio");
        Assert.notNull(usuario, "Usuario é Obrigatorio");
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", instanteCriacao=" + instanteCriacao +
                ", produto=" + produto.getNome() +
                ", usuario=" + usuario.getUsername() +
                '}';
    }
}
