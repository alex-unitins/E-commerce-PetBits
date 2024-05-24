package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AdminDTO;
import br.unitins.topicos1.dto.AdminResponseDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface AdminService {

    public AdminResponseDTO create(@Valid AdminDTO dto);

    public void update(Long id, AdminDTO dto);

    public void delete(Long id);

    public AdminResponseDTO findById(Long id);

    public List<AdminResponseDTO> findAll();

    public List<AdminResponseDTO> findByNome(String nome);

    public UsuarioResponseDTO login(String username, String senha);
}