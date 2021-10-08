package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.categoria.CategoriaRepository;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public String cadastraProduto(@Valid @RequestBody NovoProdutoRequest request, Authentication authentication) throws LoginException {
        Optional<Usuario> usuarioLogado = usuarioRepository.findByLogin(authentication.getName());
        if(usuarioLogado.isEmpty()){
            throw new LoginException("Nao existe usuario logado");
        }
        Produto produto = request.toModel(categoriaRepository,usuarioLogado.get());
        produtoRepository.save(produto);
        return produto.toString();
    }
}
