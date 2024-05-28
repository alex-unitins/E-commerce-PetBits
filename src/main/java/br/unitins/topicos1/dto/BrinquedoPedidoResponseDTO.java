package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.BrinquedoPedido;

public record BrinquedoPedidoResponseDTO (
    Long id,
    String nome,
    Double desconto,
    Integer quantidade
) {
    public static BrinquedoPedidoResponseDTO valueOf(BrinquedoPedido item) {
        return new BrinquedoPedidoResponseDTO(
            item.getId(), 
            item.getBrinquedo().getNome(), 
            item.getDesconto(),
            item.getQuantidade());
    }
}