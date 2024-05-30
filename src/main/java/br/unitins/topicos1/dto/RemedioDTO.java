package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RemedioDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 4 e 60 caracteres.")
    String nome,
    @NotNull(message = "O preço não pode ser nulo ou vazio")
    Double preco,
    @NotBlank(message = "A Descrição não pode ser nula ou vazia")
    @Size(min = 4, max = 180, message = "A descrição deve ser entre 4 e 180 caracteres.")
    String descricao,
    @NotBlank(message = "A quantidade deve ser informada, junto ao correspondente (ml)/(mg)")
    @Size(min = 4, max = 15, message = "Insira até 10 caracteres e mais que quatro, recomendado ser seguido do valor correspondente como: mg/ml")
    String quantidade,
    @NotNull(message = "Informe o id correspondete à marca deste remédio")
    Long idMarca,
    @NotBlank(message = "Informe o(s) Animal(is) que esse remédio trata")
    @Size(min = 3, max = 60, message = "O tamanho do campo animal deve ser entre 4 e 60 caracteres.")
    String animal,
    @NotNull(message = "Informe o id correspondete a faixa de idade do animal que esse remédio trata")
    Integer idIdade,
    @NotNull(message = "Informe o id correspondete a faixa de peso do animal que esse remédio trata")
    Integer idPesoAnimal,
    @NotNull(message = "O número de remédios no estoque deve ser informado")
    Integer estoque
) {
} 
