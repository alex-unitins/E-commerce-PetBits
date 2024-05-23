package br.unitins.topicos1.dto;

import java.util.List;


public record AdminDTO (
    String nome,
    String email,
    String username, 
    Double salario,
    String cargo,
    String senha,
    List<TelefoneDTO> telefones) 
{ }