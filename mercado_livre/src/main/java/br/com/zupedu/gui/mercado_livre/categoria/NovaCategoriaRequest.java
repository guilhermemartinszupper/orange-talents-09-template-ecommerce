package br.com.zupedu.gui.mercado_livre.categoria;

import br.com.zupedu.gui.mercado_livre.validator.IdExist;
import br.com.zupedu.gui.mercado_livre.validator.UniqueValue;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {
    @NotBlank @UniqueValue(domainClass = Categoria.class,nomeCampo = "nome")
    private String nome;
    @IdExist(domainClass = Categoria.class)
    private Long idCategoriaMae;

    public NovaCategoriaRequest(String nome) {
        this.nome = nome;
    }

    public NovaCategoriaRequest(String name, Long idCategoriaMae) {
        this.nome = name;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        if(idCategoriaMae != null){
            Optional<Categoria> categoriaMae = categoriaRepository.findById(idCategoriaMae);
            if(categoriaMae.isPresent()){
                return new Categoria(getNome(), categoriaMae.get());
            }else{
                throw new EntityNotFoundException("Essa Categoria Nao Existe");
            }
        }
        return new Categoria(this.nome);
    }

}
