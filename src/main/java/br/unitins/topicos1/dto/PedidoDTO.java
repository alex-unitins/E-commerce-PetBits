package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.BrinquedoPedido;
import br.unitins.topicos1.model.PetiscoPedido;
import br.unitins.topicos1.model.RacaoPedido;
import br.unitins.topicos1.model.RemedioPedido;

public record PedidoDTO(
    
    Long idCliente,
    List<RacaoPedido> racao,
    List<BrinquedoPedido> brinquedo,
    List<RemedioPedido> remedio,
    List<PetiscoPedido> petisco
) {
} 
