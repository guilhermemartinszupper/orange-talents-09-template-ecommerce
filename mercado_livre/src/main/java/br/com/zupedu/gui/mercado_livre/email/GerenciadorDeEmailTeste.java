package br.com.zupedu.gui.mercado_livre.email;

import org.springframework.stereotype.Service;

@Service
public class GerenciadorDeEmailTeste implements GerenciadorDeEmail{

    @Override
    public void enviarEmail(Email email) {
        System.out.println("Voce tem um novo email!");
        System.out.println("------------------------------------");
        System.out.println(email.getTituloEmail());
        System.out.println("Para: " + email.getDestinatario());
        System.out.println(email.getCorpoEmail());
        System.out.println("------------------------------------");
    }
}
