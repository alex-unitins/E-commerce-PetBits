package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record CartaoCreditoDTO(
    @NotBlank(message = "O número do cartão é obrigatório")
    String numero,
    @NotBlank(message = "O nome do titular é obrigatório")
    String nome,
    
    @NotBlank(message = "O CVV do cartão é obrigatório")
    String cvv,
    Double limite
) {
	
}
