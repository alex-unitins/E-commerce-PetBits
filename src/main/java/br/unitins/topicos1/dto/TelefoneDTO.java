package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO (
    String codigoArea,
    @NotBlank(message = "O número não pode ser nulo ou vazio")
    String numero
) { }