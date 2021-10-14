package br.com.zupedu.gui.mercado_livre.compra.transacao;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import br.com.zupedu.gui.mercado_livre.validator.UniqueValue;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class TransacaoRequest {
    private Long idCompra;
    private Long idTransacao;
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

    @Override
    public String toString() {
        return "TransacaoRequest{" +
                "idCompra=" + idCompra +
                ", idTransacao=" + idTransacao +
                ", status=" + status +
                ", instantePagamento=" + instantePagamento +
                '}';
    }
}
