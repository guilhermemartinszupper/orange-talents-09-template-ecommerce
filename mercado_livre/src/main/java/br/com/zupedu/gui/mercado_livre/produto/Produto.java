package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.categoria.Categoria;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
    private Categoria categorias;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @OneToMany()
    private List<FotoDeProduto> fotos = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProduto> caracteristicas, String descricao, Categoria categorias, Usuario usuario) {
        Assert.hasLength(nome, "Nome nao ser vazio");
        Assert.notNull(nome, "Nome nao pode ser nulo");
        Assert.isTrue(quantidade >= 1, "Quantidade nao pode ser menor que 1");
        Assert.isTrue(caracteristicas.size() >= 3, "Deve ter 3 caracteristicas");
        Assert.hasLength(descricao, "Descricao nao pode ser vazio");
        Assert.notNull(descricao, "Descricao nao pode ser nulo");
        Assert.notNull(usuario, "Usuario nao pode ser nulo");
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categorias = categorias;
        this.caracteristicas = caracteristicas;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void adicinaFoto(FotoDeProduto fotoDeProduto){
        Assert.notNull(fotoDeProduto, "Foto do Produto nao pode ser nula");
        this.fotos.add(fotoDeProduto);
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
                ", categoria=" + categorias +
                ", usuario=" + usuario +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }
}
