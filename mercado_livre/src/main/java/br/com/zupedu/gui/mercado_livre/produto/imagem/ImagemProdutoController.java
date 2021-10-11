package br.com.zupedu.gui.mercado_livre.produto.imagem;

import br.com.zupedu.gui.mercado_livre.handler.ProdutoNaoPertenceAoUsuarioException;
import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/images")
public class ImagemProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    private UploaderFake uploaderFake;

    @PostMapping("/{id}")
    public void uploadImagemProduto(NovasImagensRequest novasImagensRequest,
                                    @PathVariable Long id, Authentication authentication){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Produto Não Existe");
        }
        if(!authentication.getName().equals(produto.get().getUsuario().getUsername())){
          throw new ProdutoNaoPertenceAoUsuarioException("Este produto nao pertence a este usuario");
        }
        List<String> links = uploaderFake.envia(novasImagensRequest.getImagens());
        produto.get().adicionaImangens(links);
        produtoRepository.save(produto.get());
    }
}
