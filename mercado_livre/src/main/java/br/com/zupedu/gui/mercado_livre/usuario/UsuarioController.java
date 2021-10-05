package br.com.zupedu.gui.mercado_livre.usuario;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public void cadastraUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.toModel();
        entityManager.persist(usuario);
    }

}
