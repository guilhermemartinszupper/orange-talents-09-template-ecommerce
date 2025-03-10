package br.com.zupedu.gui.mercado_livre.categoria;

import br.com.zupedu.gui.mercado_livre.logger.Exemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public String cadastraCategoria(@Valid @RequestBody NovaCategoriaRequest categoriaResponse){
        Categoria categoria = categoriaResponse.toModel(categoriaRepository);
        Exemplo exemplo = new Exemplo();
        exemplo.log();
        categoriaRepository.save(categoria);
        return categoria.toString();
    }
}
