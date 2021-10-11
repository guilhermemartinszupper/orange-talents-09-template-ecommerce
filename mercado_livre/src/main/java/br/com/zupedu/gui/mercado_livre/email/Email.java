package br.com.zupedu.gui.mercado_livre.email;

public  class Email {
    private String destinatario;
    private String tituloEmail;
    private String corpoEmail;

    public Email(String destinatario, String tituloEmail, String corpoEmail) {
        this.destinatario = destinatario;
        this.tituloEmail = tituloEmail;
        this.corpoEmail = corpoEmail;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getTituloEmail() {
        return tituloEmail;
    }

    public String getCorpoEmail() {
        return corpoEmail;
    }
}
