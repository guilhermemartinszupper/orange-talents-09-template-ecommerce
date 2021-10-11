package br.com.zupedu.gui.mercado_livre.produto.pergunta;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class PerguntaProdutoResponse {
    private String titulo;

    public PerguntaProdutoResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
