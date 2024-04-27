package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Marca;

public record MarcaResponseDTO (
    Long id,
    String nome,
    String emailContato,
    String cnpj
) {
    public static MarcaResponseDTO valueOf(Marca marca) {
        return new MarcaResponseDTO(
            marca.getId(), 
            marca.getNome(), 
            marca.getEmailContato(),
            marca.getCnpj()
            );
    }
    
}
