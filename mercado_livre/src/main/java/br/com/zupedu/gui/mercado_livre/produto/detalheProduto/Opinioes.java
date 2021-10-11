package br.com.zupedu.gui.mercado_livre.produto.detalheProduto;

import br.com.zupedu.gui.mercado_livre.produto.opiniao.ProdutoOpiniao;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//Isola as operações sobre um conjunto de Opinioes
public class Opinioes {

    private List<ProdutoOpiniao> opinioes;

    public Opinioes(List<ProdutoOpiniao> opinioes) {
        this.opinioes = opinioes;
    }
    public <T> List<T> mapeiaOpinioes(Function<ProdutoOpiniao,T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toList());
    }

    public Double media() {
        List<Integer> notas = mapeiaOpinioes(ProdutoOpiniao::getNota);
        return notas.stream().mapToDouble(nota -> nota).average().orElse(0.0);
    }

    public Long numeroTotalNotas() {
        return (long) mapeiaOpinioes(ProdutoOpiniao::getNota).size();
    }
}
