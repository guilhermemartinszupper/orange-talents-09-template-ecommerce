package br.com.zupedu.gui.mercado_livre.produto.caracteristicas;

import br.com.zupedu.gui.mercado_livre.produto.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasProdutoResponse {
    private String nome;
    private String descricao;

    public CaracteristicasProdutoResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
