package br.com.zupedu.gui.mercado_livre.produto.caracteristicas;
import br.com.zupedu.gui.mercado_livre.produto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ProdutoCaracteristicaRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public ProdutoCaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoCaracteristicaRequest that = (ProdutoCaracteristicaRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public ProdutoCaracteristica toModel(Produto produto) {
        return new ProdutoCaracteristica(this.getNome(),this.getDescricao(),produto);
    }
}
