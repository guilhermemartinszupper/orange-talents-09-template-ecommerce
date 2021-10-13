package br.com.zupedu.gui.mercado_livre.email;

public  class Email {
    private String body;
    private String subject;
    private String nameFrom;
    private String from;
    private String to;

    public Email(String body, String subject, String nameFrom, String from, String to) {
        this.body = body;
        this.subject = subject;
        this.nameFrom = nameFrom;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Email{" +
                "body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", nameFrom='" + nameFrom + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
