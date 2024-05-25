package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Idade;
import br.unitins.topicos1.model.PesoProduto;
import br.unitins.topicos1.model.Racao;

public record RacaoResponseDTO(
    Long id,
    String nome,
    String nomeImagem,
    Double preco,
    String descricao,
    SaborResponseDTO sabor,
    MarcaResponseDTO marca,
    String animal,
    Idade idade,
    PesoProduto pesoProduto
) {
    public static RacaoResponseDTO valueOf(Racao racao) {
        
        return new RacaoResponseDTO(
            racao.getId(), 
            racao.getNome(),
            racao.getNomeImagem(),
            racao.getPreco(),
            racao.getDescricao(),
            SaborResponseDTO.valueOf(racao.getSabor()),
            MarcaResponseDTO.valueOf(racao.getMarca()),
            racao.getAnimal(),
            racao.getIdade(),
            racao.getPesoProduto()
            );
    }
    
}
