package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.PetiscoPedido;

public record PetiscoPedidoResponseDTO (
    Long id,
    String nome,
    Double desconto,
    Integer quantidade
) {
    public static PetiscoPedidoResponseDTO valueOf(PetiscoPedido item) {
        return new PetiscoPedidoResponseDTO(
            item.getId(), 
            item.getPetisco().getNome(), 
            item.getDesconto(),
            item.getQuantidade());
    }
}