package br.com.zupedu.gui.mercado_livre.email;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import br.com.zupedu.gui.mercado_livre.produto.pergunta.ProdutoPergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private Mailer mailer;

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
}
