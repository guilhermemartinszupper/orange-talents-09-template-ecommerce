package br.com.zupedu.gui.mercado_livre.produto;

import br.com.zupedu.gui.mercado_livre.categoria.Categoria;
import br.com.zupedu.gui.mercado_livre.categoria.CategoriaRepository;
import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.validator.IdExist;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityNotFoundException;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

public class NovoProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull @DecimalMin("0.10")
    private BigDecimal valor;
    @NotNull @Min(1)
    private Integer quantidade;
    @Size(min = 3) @NotNull
    private Set<CaracteristicaProdutoRequest> caracteristicas;
    @NotBlank @Length(min = 1,max = 1000)
    private String descricao;
    @NotNull @IdExist(domainClass = Categoria.class)
    private Long idCategoria;

    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProdutoRequest> caracteristicas, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }


    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);
        if(categoria.isPresent()){
            return new Produto(this.nome,this.valor,this.quantidade,this.caracteristicas,this.descricao,categoria.get(),usuario);
        }
        throw new EntityNotFoundException("Categoria nao foi encontrada");
    }
}
