package br.unitins.topicos1.dto;

public record BrinquedoPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idBrinquedo
) {

}