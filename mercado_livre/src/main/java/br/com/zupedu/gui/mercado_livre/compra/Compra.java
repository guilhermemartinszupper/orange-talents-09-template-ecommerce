package br.com.zupedu.gui.mercado_livre.compra;

import br.com.zupedu.gui.mercado_livre.compra.transacao.StatusTransacao;
import br.com.zupedu.gui.mercado_livre.compra.transacao.Transacao;
import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Compra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusCompra status;
    @NotNull @Enumerated(EnumType.STRING)
    private GATEWAY_PAGAMENTO gatewayPagamento;
    @ManyToOne
    private Produto produto;
    @NotNull @Positive
    private Integer quantidade;
    @Positive
    private BigDecimal preco;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario comprador;
    @OneToMany(mappedBy = "compra",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Transacao> transacoes;

    @Deprecated
    public Compra() {
    }

    public Compra(GATEWAY_PAGAMENTO gatewayPagamento, Produto produto, Integer quantidade, Usuario comprador) {
        Assert.notNull(gatewayPagamento, "GatewayPagamento nao pode ser null");
        Assert.notNull(produto, "Produto nao pode ser null");
        Assert.notNull(quantidade, "Quantidade nao pode ser null");
        Assert.notNull(comprador, "Comprador nao pode ser null");
        Assert.isTrue(quantidade > 0, "Quantidade deve ser positiva");
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.status = StatusCompra.INICIADA;
        this.preco = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public GATEWAY_PAGAMENTO getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public void adicionarTransacao(Transacao transacao){
        Assert.notNull(transacao, "Transacao nao pode ser null");
        boolean jaConcluidaComSucesso = this.transacoes.stream().anyMatch(t ->
        t.getStatusTransacao().equals(StatusTransacao.SUCESSO)
        );
        if(jaConcluidaComSucesso){
            throw new IllegalArgumentException("Ja existe uma transação concluida com sucesso para essa compra");
        }else{
            this.transacoes.add(transacao);
        }
    }

    public void concluir(){
        this.status = StatusCompra.FINALIZADA;
    }

    public static Compra build(CompraRepository compraRepository, Long idCompra){
        return compraRepository.findById(idCompra).orElseThrow(EntityNotFoundException::new);
    }


}
