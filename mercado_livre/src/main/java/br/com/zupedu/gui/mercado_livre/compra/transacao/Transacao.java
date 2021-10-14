package br.com.zupedu.gui.mercado_livre.compra.transacao;

import br.com.zupedu.gui.mercado_livre.compra.Compra;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPlataforma;
    @Enumerated(EnumType.STRING)
    private final StatusTransacao statusTransacao;
    private final LocalDateTime instantePagamento;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private final Compra compra;


    public Transacao(Compra compra, Long idPlataforma, StatusTransacao statusTransacao, LocalDateTime instantePagamento) {
        this.compra = compra;
        this.idPlataforma = idPlataforma;
        this.statusTransacao = statusTransacao;
        this.instantePagamento = instantePagamento;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    @Override
    public String toString() {
        return "TentativaPagamento{" +
                "id=" + id +
                ", idPlataforma=" + idPlataforma +
                ", statusPagamento=" + statusTransacao +
                ", instantePagamento=" + instantePagamento +
                ", compra=" + compra +
                '}';
    }
}
