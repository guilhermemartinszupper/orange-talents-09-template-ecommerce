package br.com.zupedu.gui.mercado_livre.email;

import org.springframework.stereotype.Component;

@Component
public class FakeMailer implements Mailer {

    @Override
    public void enviarEmail(Email email) {
        System.out.println("Voce tem um novo email!");
        System.out.println("------------------------------------");
        System.out.println(email);
        System.out.println("------------------------------------");
    }
}
