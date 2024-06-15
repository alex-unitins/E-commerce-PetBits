package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record PixDTO (
    @NotBlank(message = "A chave PIX é obrigatória")
    String chavePix,
    Double valor
){
    
}
