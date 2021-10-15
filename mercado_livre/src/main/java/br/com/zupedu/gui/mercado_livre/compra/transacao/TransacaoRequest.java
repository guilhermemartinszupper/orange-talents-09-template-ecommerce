package br.com.zupedu.gui.mercado_livre.compra.transacao;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import br.com.zupedu.gui.mercado_livre.validator.IdExist;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TransacaoRequest {
    @NotNull @IdExist(domainClass = Compra.class)
    private Long idCompra;
    @NotNull
    private Long idTransacao;
    @NotNull
    private StatusTransacao status;
    private LocalDateTime instantePagamento;

    public TransacaoRequest(Long idCompra, Long idTransacao, StatusTransacao status) {
        this.idCompra = idCompra;
        this.idTransacao = idTransacao;
        this.status = status;
        this.instantePagamento = LocalDateTime.now();
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Transacao toModel(Compra compra) {
        Assert.notNull(compra,"Compra nao pode ser null");
        return new Transacao(compra,this.idTransacao,this.status,this.instantePagamento);
    }
}
