package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUsuarioDTO(
    @NotBlank(message = "O username não pode ser nulo ou vazio")
    String username,
    @NotBlank(message = "A senha não pode ser nula ou vazia")
    String senha,
    
    int perfil
) {}