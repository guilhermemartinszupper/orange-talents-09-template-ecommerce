package br.com.zupedu.gui.mercado_livre.produto.opiniao;

import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Min(1) @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Length(min = 1,max = 500)
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        Assert.isTrue(nota >= 1 && nota <= 5, "Nota deve ser entre 1 e 5");
        Assert.hasLength(titulo, "Titulo nao pode ser vazio");
        Assert.notNull(titulo, "Titulo é obrigatorio");
        Assert.isTrue(descricao.length() >= 1 && descricao.length() <= 500, "Descricao deve estar entre 1 e 500");
        Assert.notNull(descricao, "descricao é obrigatorio");
        Assert.notNull(usuario, "Usuario é Obrigatorio");
        Assert.notNull(produto, "Produto é Obrigatorio");
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario.getUsername() +
                ", produto=" + produto.getNome() +
                '}';
    }
}
