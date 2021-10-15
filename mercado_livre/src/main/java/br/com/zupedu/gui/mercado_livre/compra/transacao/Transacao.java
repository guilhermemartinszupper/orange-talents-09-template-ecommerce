package br.com.zupedu.gui.mercado_livre.compra.transacao;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idPlataforma;
    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTransacao;
    private LocalDateTime instantePagamento;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(Compra compra, Long idPlataforma, StatusTransacao statusTransacao, LocalDateTime instantePagamento) {
        Assert.notNull(compra, "Compra nao pode ser null");
        Assert.notNull(idPlataforma, "idPlataforma nao pode ser null");
        Assert.notNull(statusTransacao, "Status da transacao nao pode ser null");
        Assert.notNull(instantePagamento, "Instante Pagamento nao pode ser null");
        this.compra = compra;
        this.idPlataforma = idPlataforma;
        this.statusTransacao = statusTransacao;
        this.instantePagamento = instantePagamento;
    }

    public LocalDateTime getInstantePagamento() {
        return instantePagamento;
    }

    public Long getId() {
        return id;

    }
    public Long getIdCompra(){
        return compra.getId();
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

}
