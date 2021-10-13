package br.com.zupedu.gui.mercado_livre.compra;

import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.produto.ProdutoRepository;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.validator.IdExist;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CompraRequest {

    @NotNull
    private GATEWAY_PAGAMENTO gatewayPagamento;
    @NotNull @IdExist(domainClass = Produto.class)
    private Long idProduto;
    @NotNull @Positive
    private Integer quantidade;

    public CompraRequest(GATEWAY_PAGAMENTO gatewayPagamento, Long idProduto, Integer quantidade) {
        this.gatewayPagamento = gatewayPagamento;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public GATEWAY_PAGAMENTO getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra toModel(Usuario usuario, Produto produto) {
        return new Compra(this.gatewayPagamento,produto,this.quantidade,usuario);
    }
}
