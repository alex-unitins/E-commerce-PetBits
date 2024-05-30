package br.unitins.topicos1.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PetiscoDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 4 e 60 caracteres.")
    String nome,
    @NotNull(message = "O preço não pode ser nulo ou vazio")
    Double preco,
    @NotBlank(message = "A Descrição não pode ser nula ou vazia")
    @Size(min = 4, max = 180, message = "A descrição deve ser entre 4 e 180 caracteres.")
    String descricao,
    @NotNull(message = "A quantidade de unidades do petisco deve ser informada")
    int unidades,
    @NotNull(message = "Informe o id do sabor")
    Long idSabor,
    @NotNull(message = "O id da marca correspondente a esse petisco deve ser informado")
    Long idMarca,
    @NotBlank(message = "Informe o(os) Animal(is) que esse petisco é indicado")
    @Size(min = 3, max = 60, message = "O tamanho do campo animal deve ser entre 4 e 60 caracteres.")
    String animal,
    @NotNull(message = "O id do peso do pacote do petisco deve ser informado")
    Integer idPesoProduto,
    @NotNull(message = "O número de petiscos no estoque deve ser informado")
    Integer estoque
) {
} 