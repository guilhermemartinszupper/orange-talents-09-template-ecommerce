package br.com.zupedu.gui.mercado_livre.produto.opiniao;

import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.produto.ProdutoRepository;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/opiniao")
public class ProdutoOpiniaoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/{id}")
    public String inserirOpiniao(@Valid @RequestBody OpiniaoRequest opiniaoRequest, @PathVariable Long id, Authentication authentication){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Produto Não Existe");
        }
        Optional<Usuario> usuario = usuarioRepository.findByLogin(authentication.getName());
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario Nao Existe");
        }
        ProdutoOpiniao opiniao = opiniaoRequest.toModel(produto.get(), usuario.get());
        produto.get().adicionaOpiniao(opiniao);
        produtoRepository.save(produto.get());
        return opiniao.toString();
    }

}
