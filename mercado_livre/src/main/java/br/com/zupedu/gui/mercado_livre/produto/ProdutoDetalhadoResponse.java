package br.com.zupedu.gui.mercado_livre.produto;
import br.com.zupedu.gui.mercado_livre.produto.caracteristicas.CaracteristicasProdutoResponse;
import br.com.zupedu.gui.mercado_livre.produto.imagem.ImagemDeProdutoResponse;
import br.com.zupedu.gui.mercado_livre.produto.opiniao.OpiniaoProdutoResponse;
import br.com.zupedu.gui.mercado_livre.produto.pergunta.PerguntaProdutoResponse;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalhadoResponse {
    private String nome;
    private BigDecimal preco;
    private List<ImagemDeProdutoResponse> linksImagens = new ArrayList<>();
    private Set<CaracteristicasProdutoResponse> caracteristicas;
    private String descricao;
    private Double mediaNotas = 0.0;
    private Long numeroTotalNotas = 0L;
    private List<OpiniaoProdutoResponse> opinioes = new ArrayList<>();
    private List<PerguntaProdutoResponse> perguntas = new ArrayList<>();

    @Deprecated
    public ProdutoDetalhadoResponse() {
    }

    public ProdutoDetalhadoResponse(Produto produto) {
        Assert.notNull(produto, "Produto nao pode ser null");
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicasProdutoResponse::new).collect(Collectors.toSet());
        this.descricao = produto.getDescricao();
        this.linksImagens = produto.getImagens().stream().map(ImagemDeProdutoResponse::new).collect(Collectors.toList());
        if(produto.getOpinioes() != null && !produto.getOpinioes().isEmpty()){
            this.opinioes = produto.getOpinioes().stream().map(OpiniaoProdutoResponse::new).collect(Collectors.toList());
            this.mediaNotas = opinioes.stream().mapToDouble(OpiniaoProdutoResponse::getNota).average().getAsDouble();
            this.numeroTotalNotas = opinioes.stream().mapToInt(OpiniaoProdutoResponse::getNota).count();
        }
        this.perguntas = produto.getPerguntas().stream().map(PerguntaProdutoResponse::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<ImagemDeProdutoResponse> getLinksImagens() {
        return linksImagens;
    }

    public Set<CaracteristicasProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Long getNumeroTotalNotas() {
        return numeroTotalNotas;
    }

    public List<OpiniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaProdutoResponse> getPerguntas() {
        return perguntas;
    }
}
