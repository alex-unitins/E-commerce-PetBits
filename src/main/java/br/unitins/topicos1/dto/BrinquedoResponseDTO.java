package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Brinquedo;
import br.unitins.topicos1.model.TipoBrinquedo;

public record BrinquedoResponseDTO(
    Long id,
    String nome,
    Double preco,
    String descricao,
    MarcaResponseDTO marca,
    String animal,
    String material,
    TipoBrinquedo tipoBrinquedo,
    String nomeImagem,
    int estoque

) {
    public static BrinquedoResponseDTO valueOf(Brinquedo brinquedo) {
        
        return new BrinquedoResponseDTO(
            brinquedo.getId(), 
            brinquedo.getNome(),
            brinquedo.getPreco(),
            brinquedo.getDescricao(),
            MarcaResponseDTO.valueOf(brinquedo.getMarca()),
            brinquedo.getAnimal(),
            brinquedo.getMaterial(),
            brinquedo.getTipoBrinquedo(),
            brinquedo.getNomeImagem(),
            brinquedo.getEstoque()
            );
    }
    
}
