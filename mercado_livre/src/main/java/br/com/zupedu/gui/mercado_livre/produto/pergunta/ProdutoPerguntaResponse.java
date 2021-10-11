package br.com.zupedu.gui.mercado_livre.produto.pergunta;

public class ProdutoPerguntaResponse {
    private String titulo;

    public ProdutoPerguntaResponse(ProdutoPergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
