package br.com.zupedu.gui.mercado_livre.compra.transacao.externals.notaFiscal;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/extern/notaFiscal")
public class notaFiscalController {

    @PostMapping("{idCompra}/{idUsuario}")
    String gerarNotaFiscal(@PathVariable Long idCompra,@PathVariable Long idUsuario){
        return "Nota Fiscal Gerada: " + idCompra + idUsuario + UUID.randomUUID();
    }
}
