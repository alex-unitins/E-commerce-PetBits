package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Etiqueta;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Produto;

public record ProdutoResponseDTO(
    Long id,
    String descricao,
    Marca marca,
    Etiqueta etiqueta1,
    Etiqueta etiqueta2,
    Etiqueta etiqueta3,
    Float preco
) {
    public static ProdutoResponseDTO valueOf(Produto produto) {
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getDescricao(),
            produto.getMarca(),
            produto.getEtiqueta1(),
            produto.getEtiqueta2(),
            produto.getEtiqueta3(),
            produto.getPreco());
    }
}
