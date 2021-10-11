package br.com.zupedu.gui.mercado_livre.produto.opiniao;


public class OpiniaoProdutoResponse {
    private Integer nota;
    private String titulo;
    private String descricao;

    public OpiniaoProdutoResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
