package br.com.zupedu.gui.mercado_livre.produto;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaProdutoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaProdutoRequest(String nome, String descricao) {
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
        CaracteristicaProdutoRequest that = (CaracteristicaProdutoRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public CaracteristicaProduto toModel() {
        return new CaracteristicaProduto(this.getNome(),this.getDescricao());
    }
}
