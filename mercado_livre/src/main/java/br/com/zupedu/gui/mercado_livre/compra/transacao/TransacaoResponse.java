package br.com.zupedu.gui.mercado_livre.compra.transacao;

import java.time.LocalDateTime;

public class TransacaoResponse {
    private Long idTransacao;
    private StatusTransacao statusTransacao;
    private LocalDateTime instantePagamento;
    private Long idCompra;

    public TransacaoResponse(Transacao transacao) {
        this.idTransacao = transacao.getId();
        this.statusTransacao = transacao.getStatusTransacao();
        this.instantePagamento = transacao.getInstantePagamento();
        this.idCompra = transacao.getIdCompra();

    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public LocalDateTime getInstantePagamento() {
        return instantePagamento;
    }

    public Long getIdCompra() {
        return idCompra;
    }
}
