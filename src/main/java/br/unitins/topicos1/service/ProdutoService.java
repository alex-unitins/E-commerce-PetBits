package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;

public interface ProdutoService {

    public ProdutoResponseDTO create(@Valid ProdutoDTO dto);
    public void update(Long id,@Valid ProdutoDTO dto);
    public void delete(Long id);
    public ProdutoResponseDTO findById(Long id);
    public List<ProdutoResponseDTO> findAll();
    public List<ProdutoResponseDTO> findByNome(String descricao);
   

}
