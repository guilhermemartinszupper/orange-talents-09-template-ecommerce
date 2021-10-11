package br.com.zupedu.gui.mercado_livre.produto.opiniao;

import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoRequest {
    @NotNull @Min(1) @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Length(min = 1,max = 500)
    private String descricao;

    public OpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "OpiniaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Opiniao toModel(Produto produto,Usuario usuario) {
        Assert.notNull(produto, "Produto nao pode ser null");
        Assert.notNull(usuario, "Usuario nao pode ser null");
        return new Opiniao(this.nota,this.titulo,this.descricao,usuario,produto);
    }
}
