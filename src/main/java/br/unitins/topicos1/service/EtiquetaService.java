package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EtiquetaDTO;
import br.unitins.topicos1.dto.EtiquetaResponseDTO;

import jakarta.validation.Valid;

public interface EtiquetaService {

    EtiquetaResponseDTO create(@Valid EtiquetaDTO dto);
    
    void update(Long id,@Valid EtiquetaDTO dto);
    
    void delete(Long id);
    
    EtiquetaResponseDTO findById(Long id);
    
    List<EtiquetaResponseDTO> findAll();
    List<EtiquetaResponseDTO> findByNome(String nome);
}
