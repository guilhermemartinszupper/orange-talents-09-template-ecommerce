package br.com.zupedu.gui.mercado_livre.categoria;

import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    void deleteByNome(String nome);
}
