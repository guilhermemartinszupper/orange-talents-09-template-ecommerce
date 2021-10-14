package br.com.zupedu.gui.mercado_livre.compra.transacao.externals.notaFiscal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "nota-fiscal-client",url = "localhost:8080/extern/notaFiscal")
public interface NotaFiscalClient {
    @PostMapping("{idCompra}/{idUsuario}")
    String gerarNotaFiscal(@PathVariable Long idCompra,@PathVariable Long idUsuario);
}
