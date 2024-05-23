package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Endereco;

public record ClienteDTO (
    String cpf,
    String nome,
    String email,
    Endereco endereco,
    String username,
    String senha,
    List<TelefoneDTO> telefones) 
{ }