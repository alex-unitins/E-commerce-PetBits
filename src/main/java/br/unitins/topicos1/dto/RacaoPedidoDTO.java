package br.unitins.topicos1.dto;

public record RacaoPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idRacao
) {

}