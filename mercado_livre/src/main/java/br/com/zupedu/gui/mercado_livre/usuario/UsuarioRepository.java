package br.com.zupedu.gui.mercado_livre.usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}
