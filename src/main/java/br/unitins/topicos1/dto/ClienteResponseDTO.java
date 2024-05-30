package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Endereco;


public record ClienteResponseDTO(
    Long id,
    String cpf,
    String nome,
    String username,
    String email,
    Endereco endereco,
    List<TelefoneResponseDTO> telefones
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        List<TelefoneResponseDTO> lista = cliente.getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getCpf(),
            cliente.getNome(), 
            cliente.getUsuario().getUsername(),
            cliente.getEmail(),
            cliente.getEndereco(),
            lista);
    }
    
}