package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SaborDTO (
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 4 e 60 caracteres.")
    String nome,
    @NotBlank(message = "A intensidade do sabor deve ser informada")
    @Size(min = 4, max = 20, message = "Deve conter entre 4 e 20 caracteres")
    String intensidade
) { }
