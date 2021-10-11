package br.com.zupedu.gui.mercado_livre.produto;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class CaracteristicaProduto {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @ManyToOne()
    private Produto produto;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        Assert.hasLength(nome, "Nome nao pode ser vazio");
        Assert.hasLength(descricao, "Descricao nao pode ser vazio");
        Assert.notNull(nome, "Nome nao pode ser nulo");
        Assert.notNull(descricao, "Descricao nao pode ser nulo");
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "CaracteristicaProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
