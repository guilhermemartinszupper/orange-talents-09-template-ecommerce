package br.com.zupedu.gui.mercado_livre.compra;

import br.com.zupedu.gui.mercado_livre.email.EmailSender;
import br.com.zupedu.gui.mercado_livre.handler.QuantidadeInsuficienteNoEstoqueException;
import br.com.zupedu.gui.mercado_livre.produto.Produto;
import br.com.zupedu.gui.mercado_livre.produto.ProdutoRepository;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class NovaCompraController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    EmailSender emailSender;



    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public RedirectView finalizarCompra(@RequestBody @Valid CompraRequest compraRequest, Authentication authentication, RedirectAttributes attributes){
        Optional<Usuario> usuario = usuarioRepository.findByLogin(authentication.getName());
        Optional<Produto> produto = produtoRepository.findById(compraRequest.getIdProduto());
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario Nao Existe");
        }
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Produto nao encontrado");
        }
        if(produto.get().retirarEstoque(compraRequest.getQuantidade())){
            Compra compra = compraRequest.toModel(usuario.get(), produto.get());
            emailSender.novaCompra(compra);
            compraRepository.save(compra);
            return UrlPaymentGenerator.generatePaymentURL(compra, attributes);
        }else{
            throw new QuantidadeInsuficienteNoEstoqueException("Quantidade no estoque insuficiente.");
        }
    }
}
