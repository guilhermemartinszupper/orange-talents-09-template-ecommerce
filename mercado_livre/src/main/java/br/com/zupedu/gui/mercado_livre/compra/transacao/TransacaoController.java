package br.com.zupedu.gui.mercado_livre.compra.transacao;

import br.com.zupedu.gui.mercado_livre.compra.Compra;
import br.com.zupedu.gui.mercado_livre.compra.CompraRepository;
import br.com.zupedu.gui.mercado_livre.compra.StatusCompra;
import br.com.zupedu.gui.mercado_livre.compra.transacao.externals.rankVendedores.RankingVendedorClient;
import br.com.zupedu.gui.mercado_livre.compra.transacao.externals.notaFiscal.NotaFiscalClient;
import br.com.zupedu.gui.mercado_livre.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/compras/retorno")
public class TransacaoController {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    NotaFiscalClient notaFiscalClient;
//    @Autowired
//    RankingVendedorClient rankingVendedorClient;
    @Autowired
    EmailSender emailSender;

    @PostMapping
    public String retorno(@RequestBody TransacaoRequest request){
        Optional<Compra> compra = compraRepository.findById(request.getIdCompra());
        if(compra.isEmpty()){
            throw new EntityNotFoundException("Essa compra não existe no sistema");
        }
        Transacao transacao = request.toModel(compra.get());
        transacaoRepository.save(transacao);
        Long idComprador = compra.get().getComprador().getId();
        Long idCompra = compra.get().getId();
        Long idProduto = compra.get().getProduto().getId();
        if(transacao.getStatusTransacao().equals(StatusTransacao.SUCESSO)){
            compra.get().concluir(StatusCompra.FINALIZADA);
            notaFiscalClient.gerarNotaFiscal(idCompra, idComprador);
//            rankingVendedorClient.novaVenda(idCompra,idProduto);
//            emailSender.pagamentoSucesso(compra.get());
            return transacao.getStatusTransacao().toString();
        }
//        emailSender.pagamentoFalha(compra.get());
        return transacao.getStatusTransacao().toString();
    }
}
