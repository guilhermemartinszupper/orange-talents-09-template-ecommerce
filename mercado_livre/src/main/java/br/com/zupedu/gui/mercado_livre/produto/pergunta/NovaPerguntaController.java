package br.com.zupedu.gui.mercado_livre.produto.pergunta;

import br.com.zupedu.gui.mercado_livre.email.Email;
import br.com.zupedu.gui.mercado_livre.email.EmailSender;
import br.com.zupedu.gui.mercado_livre.email.FakeMailer;
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
@RequestMapping("/produtos/perguntas")
public class NovaPerguntaController {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmailSender emailSender;


    @PostMapping("/{id}")
    public String inserirResposta(@Valid @RequestBody ProdutoPerguntaRequest perguntaRequest, @PathVariable Long id, Authentication authentication){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Produto Não Existe");
        }
        Optional<Usuario> usuario = usuarioRepository.findByLogin(authentication.getName());
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario Nao Existe");
        }
        ProdutoPergunta pergunta = perguntaRequest.toModel(produto.get(), usuario.get());
        produto.get().adicionaPergunta(pergunta);
        produtoRepository.save(produto.get());
        emailSender.novaPergunta(pergunta);
        return pergunta.toString();
    }
}
