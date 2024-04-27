package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.SaborDTO;
import br.unitins.topicos1.dto.SaborResponseDTO;
import jakarta.validation.Valid;

public interface SaborService {
    public SaborResponseDTO create(@Valid SaborDTO dto);
    public void update(Long id, SaborDTO dto);
    public void delete(Long id);
    public SaborResponseDTO findById(Long id);
    public List<SaborResponseDTO> findAll();
    public List<SaborResponseDTO> findByNome(String nome);
}
