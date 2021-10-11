package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.categoria.Categoria;
import br.com.zupedu.gui.mercado_livre.produto.imagem.ImagemDeProduto;
import br.com.zupedu.gui.mercado_livre.produto.opiniao.Opiniao;
import br.com.zupedu.gui.mercado_livre.produto.pergunta.Pergunta;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Size(min = 3) @NotNull @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "produto")
    private Set<CaracteristicaProduto> caracteristicas;
    @NotBlank @Length(min = 1,max = 1000) @Column(columnDefinition = "Text",length = 1000,nullable = false)
    private String descricao;
    @NotNull @ManyToOne()
    private Categoria categorias;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto",fetch = FetchType.LAZY)
    private List<ImagemDeProduto> fotos = new ArrayList<>();
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto",fetch = FetchType.LAZY)
    private List<Opiniao> opinioes;
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto",fetch = FetchType.LAZY)
    private List<Pergunta> perguntas;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProdutoRequest> caracteristicas, String descricao, Categoria categorias, Usuario usuario) {
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
        this.caracteristicas = caracteristicas.stream()
                .map(c -> c.toModel(this)).collect(Collectors.toSet());
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void adicinaFoto(ImagemDeProduto fotoDeProduto){
        Assert.notNull(fotoDeProduto, "Foto do Produto nao pode ser nula");
        this.fotos.add(fotoDeProduto);
    }

    public void adicionaOpiniao(Opiniao opiniao) {
        Assert.notNull(opiniao, "Opiniao nao pode ser nula");
        this.opinioes.add(opiniao);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionaPergunta(Pergunta pergunta) {
        Assert.notNull(pergunta, "Pergunta nao pode ser null");
        this.perguntas.add(pergunta);
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
                ", categorias=" + categorias +
                ", usuario=" + usuario +
                ", instanteCadastro=" + instanteCadastro +
                ", fotos=" + fotos +
                ", opinioes=" + opinioes +
                ", perguntas=" + perguntas +
                '}';
    }
}
