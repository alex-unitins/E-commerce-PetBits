package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.RemedioPedido;

public record RemedioPedidoResponseDTO (
    Long id,
    String nome,
    Double desconto,
    Integer quantidade,
    Long idRemedio
) {
    public static RemedioPedidoResponseDTO valueOf(RemedioPedido item) {
        return new RemedioPedidoResponseDTO(
            item.getId(), 
            item.getRemedio().getNome(), 
            item.getDesconto(),
            item.getQuantidade(),
            item.getRemedio().getId());
    }
}