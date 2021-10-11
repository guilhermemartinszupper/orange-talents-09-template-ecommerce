package br.com.zupedu.gui.mercado_livre.produto.detalheProduto;
import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.produto.caracteristicas.DetalheProdutoCaracteristica;
import br.com.zupedu.gui.mercado_livre.produto.imagem.ProdutoImagem;
import br.com.zupedu.gui.mercado_livre.produto.opiniao.ProdutoOpiniao;
import br.com.zupedu.gui.mercado_livre.produto.opiniao.ProdutoOpiniaoResponse;
import br.com.zupedu.gui.mercado_livre.produto.pergunta.ProdutoPerguntaResponse;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProdutoDetalhadoResponse {
    private String nome;
    private BigDecimal preco;
    private List<String> linksImagens;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private String descricao;
    private Double mediaNotas;
    private Long numeroTotalNotas = 0L;
    private List<Map<String,String>> opinioes;
    private List<ProdutoPerguntaResponse> perguntas;

    @Deprecated
    public ProdutoDetalhadoResponse() {
    }

    public ProdutoDetalhadoResponse(Produto produto) {
        Assert.notNull(produto, "Produto nao pode ser null");
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        //Forma Antiga
        //this.caracteristicas = produto.getCaracteristicas().stream().map(DetalheProdutoCaracteristica::new).collect(Collectors.toSet());
        //Forma Nova -> Nao expoe a coleção de caracteristicas para que a pessoa possa muda-las.
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapeiaLinksImagens(ProdutoImagem::getLink);
        this.perguntas = produto.mapeiaPerguntas(ProdutoPerguntaResponse::new);
        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(),"descricao", opiniao.getDescricao(),"nota",opiniao.getNota().toString());
        });
        this.mediaNotas = opinioes.media();
        this.numeroTotalNotas = opinioes.numeroTotalNotas();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
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

    public List<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public List<ProdutoPerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
