package br.unitins.topicos1.dto;

public record RemedioPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idRemedio
) {

}