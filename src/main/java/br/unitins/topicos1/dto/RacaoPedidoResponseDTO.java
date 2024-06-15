package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.RacaoPedido;

public record RacaoPedidoResponseDTO (
    Long id,
    String nome,
    Double desconto,
    Integer quantidade,
    Long idRacao
) {
    public static RacaoPedidoResponseDTO valueOf(RacaoPedido item) {
        return new RacaoPedidoResponseDTO(
            item.getId(), 
            item.getRacao().getNome(), 
            item.getDesconto(),
            item.getQuantidade(),
            item.getRacao().getId());
    }
}