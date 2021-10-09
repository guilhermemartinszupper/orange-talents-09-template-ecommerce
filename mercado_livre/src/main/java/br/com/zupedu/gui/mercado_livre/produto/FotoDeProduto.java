package br.com.zupedu.gui.mercado_livre.produto;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class FotoDeProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private Long tamanho;
    @NotBlank @Column(columnDefinition = "BLOB")
    private String encodedFile;


    public FotoDeProduto(String nome, Long tamanho, String encodedFile) {
        Assert.hasLength(nome,"Nome nao pode estar em branco");
        Assert.hasLength(encodedFile,"Encode da foto nao pode estar em branco");
        Assert.notNull(nome, "Nome nao pode ser nulo");
        Assert.notNull(encodedFile, "Encode nao pode ser nulo");
        Assert.notNull(tamanho, "Tamanho nao pode ser nulo");
        Assert.isTrue(tamanho > 0,"Tamanho deve ser positivo");
        this.nome = nome;
        this.tamanho = tamanho;
        this.encodedFile = encodedFile;
    }
}
