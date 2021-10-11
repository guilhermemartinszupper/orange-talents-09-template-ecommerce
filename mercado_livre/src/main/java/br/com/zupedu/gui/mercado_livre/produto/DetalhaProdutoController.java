package br.com.zupedu.gui.mercado_livre.produto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/detalha")
public class DetalhaProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public ProdutoDetalhadoResponse detalhaProduto(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Produto Não Existe");
        }
        return new ProdutoDetalhadoResponse(produto.get());
    }
}
