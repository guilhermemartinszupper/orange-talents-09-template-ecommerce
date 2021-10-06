package br.com.zupedu.gui.mercado_livre.usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    void deleteByLogin(String login);
}
