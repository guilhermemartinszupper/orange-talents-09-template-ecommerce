package br.com.zupedu.gui.mercado_livre.handler;

public class QuantidadeInsuficienteNoEstoqueException extends RuntimeException {
    public QuantidadeInsuficienteNoEstoqueException(String s) {
        super(s);
    }
}
