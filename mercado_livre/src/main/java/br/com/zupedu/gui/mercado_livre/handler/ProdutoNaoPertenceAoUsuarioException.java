package br.com.zupedu.gui.mercado_livre.handler;

public class ProdutoNaoPertenceAoUsuarioException extends RuntimeException {
    public ProdutoNaoPertenceAoUsuarioException(String s) {
        super(s);
    }
}
