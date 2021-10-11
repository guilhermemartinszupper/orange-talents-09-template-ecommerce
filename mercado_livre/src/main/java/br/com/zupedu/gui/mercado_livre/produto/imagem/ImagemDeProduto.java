package br.com.zupedu.gui.mercado_livre.produto.imagem;
import br.com.zupedu.gui.mercado_livre.produto.Produto;
import org.springframework.util.Assert;

import javax.persistence.*;


@Entity
public class ImagemDeProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long tamanho;
  @Column(columnDefinition = "MEDIUMBLOB")
    private String encodedFile;
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @Deprecated
    public ImagemDeProduto() {
    }

    public ImagemDeProduto(String nome, Long tamanho, String encodedFile, Produto produto) {
        Assert.hasLength(nome,"Nome nao pode estar em branco");
        Assert.hasLength(encodedFile,"Encode da foto nao pode estar em branco");
        Assert.notNull(nome, "Nome nao pode ser nulo");
        Assert.notNull(encodedFile, "Encode nao pode ser nulo");
        Assert.notNull(tamanho, "Tamanho nao pode ser nulo");
        Assert.isTrue(tamanho > 0,"Tamanho deve ser positivo");
        this.nome = nome;
        this.tamanho = tamanho;
        this.encodedFile = encodedFile;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public String getEncodedFile() {
        return encodedFile;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "FotoDeProduto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", encodedFile='" + encodedFile + '\'' +
                ", produto=" + produto.getId() +
                '}';
    }
}
