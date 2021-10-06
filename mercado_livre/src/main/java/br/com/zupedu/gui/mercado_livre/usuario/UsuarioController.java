package br.com.zupedu.gui.mercado_livre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public String cadastraUsuario(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest){
        Usuario usuario = novoUsuarioRequest.toModel();
        usuarioRepository.save(usuario);
        return usuario.toString();
    }

}
