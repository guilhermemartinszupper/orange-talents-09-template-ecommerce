package br.com.zupedu.gui.mercado_livre.email;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import br.com.zupedu.gui.mercado_livre.compra.transacao.StatusTransacao;
import br.com.zupedu.gui.mercado_livre.compra.transacao.Transacao;
import br.com.zupedu.gui.mercado_livre.produto.pergunta.ProdutoPergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailSender {

    @Autowired
    private FakeMailer mailer;

    public void novaPergunta(ProdutoPergunta pergunta){
        String body = "O usuario" + pergunta.getUsuario().getUsername() + "fez a seguinte pergunta" + pergunta.getTitulo();
        String subject = "Nova Pergunta Produto: " + pergunta.getProduto().getNome();
        String nameFrom = pergunta.getUsuario().getUsername();
        String from = "novapergunta@mercadolivre.com";
        String to = pergunta.getProduto().getUsuario().getUsername();
        Email email = new Email(body,subject,nameFrom,from,to);
        mailer.enviarEmail(email);
    }

    public void novaCompra(Compra compra) {
        String body = "O usuario " + compra.getComprador().getUsername() + " Deseja comprar " +
                compra.getQuantidade() + "X de " + compra.getProduto().getNome();
        String subject = "Nova Compra Produto: " + compra.getProduto().getNome();
        String nameFrom = compra.getComprador().getUsername();
        String from = "novacompra@mercadolivre.com";
        String to = compra.getProduto().getUsuario().getUsername();
        Email email = new Email(body,subject,nameFrom,from,to);
        mailer.enviarEmail(email);
    }

    public void novaTransacao(Compra compra, Transacao transacao) {
        String subject = "Transacao" + transacao.getId() + " da Compra " + compra.getId() + "Com " + transacao.getStatusTransacao();
        String nameFrom = compra.getGatewayPagamento().name();
        String from = "novatransacao@mercadolivre.com";
        String to = compra.getComprador().getUsername();
        String body = "";
        String codigoCompra = compra.getId().toString();
        String gatewayPagamento = compra.getGatewayPagamento().name();
        String valor = compra.getPreco().toString();
        String codigoTransacao = transacao.getId().toString();
        String instantePagamento = transacao.getInstantePagamento().format(DateTimeFormatter.ISO_LOCAL_TIME);
        if(transacao.getStatusTransacao().equals(StatusTransacao.SUCESSO)){
            String status = compra.getStatus().name();
            String produto = compra.getProduto().getNome();
            String quantidade = compra.getQuantidade().toString();

            body = String.format("Transação realizada com sucesso! %n" +
                    "Codigo da Compra: %s%n" +
                    "Status: %s%n" +
                    "Gateway Pagamento: %s%n" +
                    "Produto: %s%n" +
                    "Quantidade: %s%n" +
                    "Valor: %s%n" +
                    "Codigo da Transação: %s%n" +
                    "Instante do Pagamento: %s%n",
                    codigoCompra,status,gatewayPagamento,produto,quantidade,valor,codigoTransacao,instantePagamento);
        }else{
            body =String.format("Transação com Falha tente novamente %n" +
                    "Codigo da Compra: %s%n" +
                    "Gateway Pagamento: %s%n" +
                    "Valor: %s%n" +
                    "Codigo da Transação: %s%n" +
                    "Instante do Pagamento: %s%n",codigoCompra,gatewayPagamento,valor,codigoTransacao,instantePagamento);
        }
        Email email = new Email(body,subject,nameFrom,from,to);
        mailer.enviarEmail(email);
    }
}
