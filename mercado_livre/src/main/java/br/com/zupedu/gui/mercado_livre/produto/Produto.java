package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.categoria.Categoria;
import br.com.zupedu.gui.mercado_livre.handler.QuantidadeInsuficienteNoEstoqueException;
import br.com.zupedu.gui.mercado_livre.produto.caracteristicas.ProdutoCaracteristica;
import br.com.zupedu.gui.mercado_livre.produto.caracteristicas.ProdutoCaracteristicaRequest;
import br.com.zupedu.gui.mercado_livre.produto.detalheProduto.Opinioes;
import br.com.zupedu.gui.mercado_livre.produto.imagem.ProdutoImagem;
import br.com.zupedu.gui.mercado_livre.produto.opiniao.ProdutoOpiniao;
import br.com.zupedu.gui.mercado_livre.produto.pergunta.ProdutoPergunta;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @NotBlank @Column(nullable = false)
    private String nome;
    @NotNull @DecimalMin("0.10") @Column(nullable = false)
    private BigDecimal valor;
    @NotNull @PositiveOrZero @Column(nullable = false)
    private Integer quantidade;
    @Size(min = 3) @NotNull @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "produto")
    private Set<ProdutoCaracteristica> caracteristicas;
    @NotBlank @Length(min = 1,max = 1000) @Column(columnDefinition = "Text",length = 1000,nullable = false)
    private String descricao;
    @NotNull @ManyToOne()
    private Categoria categorias;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto",fetch = FetchType.LAZY)
    private List<ProdutoImagem> imagens = new ArrayList<>();
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto",fetch = FetchType.LAZY)
    private List<ProdutoOpiniao> opinioes;
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto",fetch = FetchType.LAZY)
    private List<ProdutoPergunta> perguntas;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<ProdutoCaracteristicaRequest> caracteristicas, String descricao, Categoria categorias, Usuario usuario) {
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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public <T> Set<T> mapeiaCaracteristicas(Function<ProdutoCaracteristica,T> funcaoMapeadora){
        return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> List<T> mapeiaLinksImagens(Function<ProdutoImagem, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toList());
    }

    public <T> List<T> mapeiaPerguntas(Function<ProdutoPergunta, T> funcaoMapeadora){
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toList());
    }

    public void adicionaImagens(List<String> links){
        Assert.notNull(links, "Links das imagens nao podem ser nulos");
        Assert.isTrue(!links.isEmpty(), "Links das imagens nao podem ser vazios");
        this.imagens.addAll(links.stream().map(s -> new ProdutoImagem(s, this)).collect(Collectors.toList()));
    }

    public void adicionaOpiniao(ProdutoOpiniao opiniao) {
        Assert.notNull(opiniao, "Opiniao nao pode ser nula");
        this.opinioes.add(opiniao);
    }
    public void adicionaPergunta(ProdutoPergunta pergunta) {
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
                ", fotos=" + imagens +
                ", opinioes=" + opinioes +
                ", perguntas=" + perguntas +
                '}';
    }

    public boolean retirarEstoque(Integer quantidade) {
        Assert.isTrue(quantidade > 0, "quantidade deve ser positiva");
        if(this.quantidade < quantidade){
            return false;
        }
        this.quantidade -= quantidade;
        return true;
    }
}
