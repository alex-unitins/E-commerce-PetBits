package br.unitins.topicos1.dto;

public record PetiscoPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idBrinquedo
) {

}