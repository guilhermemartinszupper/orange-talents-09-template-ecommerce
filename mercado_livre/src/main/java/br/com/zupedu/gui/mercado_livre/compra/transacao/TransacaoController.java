package br.com.zupedu.gui.mercado_livre.compra.transacao;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import br.com.zupedu.gui.mercado_livre.compra.CompraRepository;
import br.com.zupedu.gui.mercado_livre.compra.transacao.externals.rankVendedores.RankingVendedorClient;
import br.com.zupedu.gui.mercado_livre.compra.transacao.externals.notaFiscal.NotaFiscalClient;
import br.com.zupedu.gui.mercado_livre.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras/retorno")
public class TransacaoController {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    NotaFiscalClient notaFiscalClient;
    @Autowired
    RankingVendedorClient rankingVendedorClient;
    @Autowired
    EmailSender emailSender;

    @PostMapping
    public TransacaoResponse retorno(@RequestBody TransacaoRequest request){
        Compra compra = Compra.build(compraRepository, request.getIdCompra());
        Transacao transacao = request.toModel(compra);
        compra.adicionarTransacao(transacao);
        transacaoRepository.save(transacao);
        Long idComprador = compra.getComprador().getId();
        Long idCompra = compra.getId();
        Long idVendedor = compra.getProduto().getUsuario().getId();
        if(transacao.getStatusTransacao().equals(StatusTransacao.SUCESSO)){
            compra.concluir();
            compraRepository.save(compra);
            notaFiscalClient.gerarNotaFiscal(idCompra, idComprador);
            rankingVendedorClient.inserirNovaVendaRank(idVendedor,idCompra);
        }
        emailSender.novaTransacao(compra, transacao);
        return new TransacaoResponse(transacao);
    }
}
