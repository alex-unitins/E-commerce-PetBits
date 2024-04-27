package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Idade;
import br.unitins.topicos1.model.PesoAnimal;
import br.unitins.topicos1.model.Remedio;

public record RemedioResponseDTO(
    Long id,
    String nome,
    Double preco,
    String descricao,
    String quantidade,
    MarcaResponseDTO marca,
    String animal,
    Idade idade,
    PesoAnimal pesoAnimal
    
) {
    public static RemedioResponseDTO valueOf(Remedio remedio) {
        
        return new RemedioResponseDTO(
            remedio.getId(), 
            remedio.getNome(),
            remedio.getPreco(),
            remedio.getDescricao(),
            remedio.getQuantidade(),
            MarcaResponseDTO.valueOf(remedio.getMarca()),
            remedio.getAnimal(),
            remedio.getIdade(),
            remedio.getPesoAnimal()
            );
    }
    
}
