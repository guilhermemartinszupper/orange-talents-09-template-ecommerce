package br.com.zupedu.gui.mercado_livre.produto.imagem;

public class ImagemDeProdutoResponse {
    private String link;

    public ImagemDeProdutoResponse(ImagemDeProduto imagemDeProduto) {
        this.link = imagemDeProduto.getLink();
    }

    public String getLink() {
        return link;
    }
}
