package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EtiquetaDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size( max = 60, message = "O tamanho do nome deve ser de até 60 caracteres.")
    String nome
) { }
