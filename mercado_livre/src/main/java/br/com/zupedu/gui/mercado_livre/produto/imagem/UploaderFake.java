package br.com.zupedu.gui.mercado_livre.produto.imagem;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake {
    /*
    Links para imagens que foram upadas.
     */
    public List<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(file -> "http://bucket.io/"+file.getOriginalFilename()
                        .replaceAll("\\s","")
                        + UUID.randomUUID().toString())
                .collect(Collectors.toList());
    }
}
