package br.com.zupedu.gui.mercado_livre.compra.transacao.externals.rankVendedores;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/extern/rankVendedores")
public class RankingVendedorController {

    @PostMapping("{idVendedor}/{idCompra}")
    String inserirNovaVendaRank(@PathVariable Long idVendedor, @PathVariable Long idCompra){
        return "Vendedor" + idVendedor + "inserido no rank";
    }
}
