package br.com.zupedu.gui.mercado_livre.compra;

import org.springframework.util.Assert;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

public class UrlPaymentGenerator {

    public static RedirectView generatePaymentURL(Compra compra, RedirectAttributes attributes){
        Assert.notNull(compra, "Compra nao pode ser nulla");
        Assert.notNull(compra.getGatewayPagamento(), "È necessario ter um gateway de pagamento");
        RedirectView redirectView = new RedirectView();
        String idCompra = compra.getId().toString();
        String redirectUrl = "/compras/retornoPagamento/" + idCompra;
        String url = null;
        if(compra.getGatewayPagamento().equals(GATEWAY_PAGAMENTO.PAYPAL)){
            url = "//paypal.com/";
        }
        if(compra.getGatewayPagamento().equals(GATEWAY_PAGAMENTO.PAGSEGURO)){
            url =  "//pagseguro.com/";
        }
        attributes.addAttribute("buyerID",idCompra);
        attributes.addAttribute("redirectUrl",redirectUrl);
        redirectView.setUrl(url);
        return redirectView;
    }

}
