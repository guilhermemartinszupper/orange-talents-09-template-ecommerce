package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.categoria.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @NotBlank @Column(nullable = false)
    private String nome;
    @NotNull @DecimalMin("0.10") @Column(nullable = false)
    private BigDecimal valor;
    @NotNull @Min(1) @Column(nullable = false)
    private Integer quantidade;
    @Size(min = 3) @NotNull @OneToMany(cascade = CascadeType.ALL)
    private Set<CaracteristicaProduto> caracteristicas;
    @NotBlank @Length(min = 1,max = 1000) @Column(columnDefinition = "Text",length = 1000,nullable = false)
    private String descricao;
    @NotNull @ManyToOne()
    private Categoria categoria;
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProdutoRequest> caracteristicas, String descricao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        Set<CaracteristicaProduto> produtoSet = new HashSet<>();
        caracteristicas.forEach(c -> {
            CaracteristicaProduto caracteristica  = new CaracteristicaProduto(c.getNome(),c.getDescricao());
            produtoSet.add(caracteristica);
        });
        this.caracteristicas = produtoSet;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }
}
