package br.com.zupedu.gui.mercado_livre.categoria;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true, nullable = false)
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String name) {
        Assert.hasLength(name,"Nome nao pode ser vazio");
        this.nome = name;
    }

    public Categoria(String name, Categoria categoriaMae) {
        Assert.hasLength(name,"Nome nao pode ser vazio");
        Assert.notNull(categoriaMae,"Categoria Mae nao pode ser null");
        this.nome = name;
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", categoriaMae=" + (categoriaMae != null ? categoriaMae.nome : null) +
                '}';
    }
}
