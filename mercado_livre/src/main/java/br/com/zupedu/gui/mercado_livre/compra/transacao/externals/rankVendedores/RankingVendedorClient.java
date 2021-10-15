package br.com.zupedu.gui.mercado_livre.compra.transacao.externals.rankVendedores;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rank-vendedores-client",url = "localhost:8080/extern/rankVendedores")
public interface RankingVendedorClient {
    @PostMapping("{idVendedor}/{idCompra}")
    String inserirNovaVendaRank(@PathVariable Long idVendedor, @PathVariable Long idCompra);
}
