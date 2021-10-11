package br.com.zupedu.gui.mercado_livre.produto.caracteristicas;

public class DetalheProdutoCaracteristica {
    private String nome;
    private String descricao;

    public DetalheProdutoCaracteristica(ProdutoCaracteristica produtoCaracteristica) {
        this.nome = produtoCaracteristica.getNome();
        this.descricao = produtoCaracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
