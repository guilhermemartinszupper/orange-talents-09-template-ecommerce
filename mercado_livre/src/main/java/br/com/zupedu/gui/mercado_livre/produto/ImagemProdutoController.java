package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.handler.ProdutoNaoPertenceAoUsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/images")
public class ImagemProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/{id}")
    public void uploadImagemProduto(@RequestParam MultipartFile file, @PathVariable Long id, Authentication authentication) throws IOException {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Produto Não Existe");
        }
        if(!authentication.getName().equals(produto.get().getUsuario().getUsername())){
          throw new ProdutoNaoPertenceAoUsuarioException("Este produto nao pertence a este usuario");
        }
        String encodedfile = new String((Base64.getEncoder().encodeToString(file.getBytes())));
        String nome = file.getOriginalFilename();
        long tamanho = file.getSize();
        FotoDeProduto foto = new FotoDeProduto(nome,tamanho,encodedfile);
        produto.get().adicinaFoto(foto);
    }
}
