package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarcaDTO (
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 4 e 60 caracteres.")
    String nome,
    @NotBlank(message = "O email para contato deve ser informado")
    @Size(min = 5, max = 25, message = "Deve conter entre 5 e 25 caracteres")
    String emailContato,
    @NotBlank(message = "O cnpj não pode ser nulo ou vazio")
    @Size(min = 3, max = 15, message = "O cnpj deve conter 11 caracteres e aceita de 3 à 15")
    String cnpj
) { }
