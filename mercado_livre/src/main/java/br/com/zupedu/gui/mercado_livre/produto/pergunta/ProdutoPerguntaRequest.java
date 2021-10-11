package br.com.zupedu.gui.mercado_livre.produto.pergunta;

import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.validation.constraints.NotBlank;

public class ProdutoPerguntaRequest {
    @NotBlank
    private String titulo;

    @Deprecated
    public ProdutoPerguntaRequest() {
    }

    public ProdutoPerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public ProdutoPergunta toModel(Produto produto, Usuario usuario) {
        Assert.notNull(produto, "Produto nao pode ser null");
        Assert.notNull(usuario, "Usuario nao pode ser null");
        return new ProdutoPergunta(this.titulo,produto,usuario);
    }
}
