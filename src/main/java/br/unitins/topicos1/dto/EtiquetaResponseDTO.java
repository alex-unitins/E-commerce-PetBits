package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Etiqueta;

public record EtiquetaResponseDTO(
    Long id,
    String nome
) {
    public static EtiquetaResponseDTO valueOf(Etiqueta etiqueta) {
        return new EtiquetaResponseDTO(
            etiqueta.getId(), 
            etiqueta.getNome()
        );
    }
}
