package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BrinquedoDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 4 e 60 caracteres.")
    String nome,
    @NotNull(message = "O preço não pode ser nulo")
    Double preco,
    @NotBlank(message = "A Descrição não pode ser nula ou vazia")
    @Size(min = 4, max = 180, message = "A descrição deve ser entre 4 e 180 caracteres.")
    String descricao,
    @NotBlank(message = "Ao menos o material principal desse brinquedo deve ser informado")
    @Size(min = 4, max = 100, message = "O material deve conter entre 4 à 100 caracteres")
    String material,
    @NotNull(message = "O id da Marca que esse brinquedo pertence deve ser informado")
    Long idMarca,
    @NotBlank(message = "Informe o(os) Animal(is) que esse brinquedo é indicado")
    @Size(min = 3, max = 60, message = "O tamanho do campo animal deve ser entre 4 e 60 caracteres.")
    String animal,
    @NotNull(message = "O id do tipo de brinquedo deste produto deve ser informado")
    Integer idTipoBrinquedo, 
    @NotNull(message = "O número de brinquedos no estoque deve ser informado")
    Integer estoque
) {}
