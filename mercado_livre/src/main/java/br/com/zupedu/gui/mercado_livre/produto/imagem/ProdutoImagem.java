package br.com.zupedu.gui.mercado_livre.produto.imagem;
import br.com.zupedu.gui.mercado_livre.produto.Produto;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class ProdutoImagem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Column(nullable = false)
    private String link;
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @Deprecated
    public ProdutoImagem() {
    }

    public ProdutoImagem(String link, Produto produto) {
        Assert.hasLength(link,"Link nao pode estar em branco");
        Assert.notNull(link,"Link nao pode ser null");
        Assert.notNull(produto, "Produto nao pode ser null");
        this.link = link;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "ImagemDeProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", produto=" + produto.getNome() +
                '}';
    }
}
