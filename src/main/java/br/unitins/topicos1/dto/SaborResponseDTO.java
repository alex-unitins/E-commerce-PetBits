package br.unitins.topicos1.dto;


import br.unitins.topicos1.model.Sabor;

public record SaborResponseDTO (
    Long id,
    String nome,
    String intensidade
) {
    public static SaborResponseDTO valueOf(Sabor sabor) {
        return new SaborResponseDTO(
            sabor.getId(), 
            sabor.getNome(), 
            sabor.getIntensidade()
            );
    }
    
}
