package br.com.zupedu.gui.mercado_livre.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exemplo {

    private final Logger logger = LoggerFactory.getLogger(Exemplo.class);
    public void log(){
        logger.info("Long de informação");
        logger.warn("Log de aviso, algo esta errado ou faltando cuidado");
        logger.error("Log de erro, algo de errado aconteceu!");
        logger.debug("Log de deupuracao, contem informaçõies mais refinadas, que sao mais uteis para depurar" +
                "um aplicativo");
        logger.trace("Log de rastrabilidade, contem informações mais refinadas do que o DEBUG");

    }

}
