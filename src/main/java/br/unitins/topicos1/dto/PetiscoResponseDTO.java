package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.PesoProduto;
import br.unitins.topicos1.model.Petisco;

public record PetiscoResponseDTO(
    Long id,
    String nome,
    Double preco,
    String descricao,
    int unidades,
    SaborResponseDTO sabor,
    MarcaResponseDTO marca,
    String animal,
    PesoProduto pesoProduto
) {
    public static PetiscoResponseDTO valueOf(Petisco petisco) {
        
        return new PetiscoResponseDTO(
            petisco.getId(), 
            petisco.getNome(),
            petisco.getPreco(),
            petisco.getDescricao(),
            petisco.getUnidades(),
            SaborResponseDTO.valueOf(petisco.getSabor()),
            MarcaResponseDTO.valueOf(petisco.getMarca()),
            petisco.getAnimal(),
            petisco.getPesoProduto()
            );
    }
    
}
