package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Etiqueta;
import br.unitins.topicos1.model.Marca;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public record ProdutoDTO (
    @NotBlank(message = "A descrição não pode ser nula ou vazia")
    @Size(max = 100, message = "O tamanho da descricao deve ser de até 100 caracteres.")
    String descricao,
    @NotNull(message = "A marca não pode ser nula")
    Marca marca,
    @NotNull(message = "O produto deve ter ao menos uma etiqueta")
    Etiqueta etiqueta1,
    Etiqueta etiqueta2, 
    Etiqueta etiqueta3,
    @NotNull(message = "O preço não pode ser nulo")
    @PositiveOrZero(message = "O preço deve ser um valor positivo ou zero")
    Float preco
) { }
