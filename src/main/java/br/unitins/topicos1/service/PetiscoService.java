package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PetiscoDTO;
import br.unitins.topicos1.dto.PetiscoResponseDTO;
import jakarta.validation.Valid;

public interface PetiscoService {

    public PetiscoResponseDTO create(@Valid PetiscoDTO dto);
    public void update(Long id, PetiscoDTO dto);
    public void delete(Long id);
    public PetiscoResponseDTO findById(Long id);
    public List<PetiscoResponseDTO> findAll();
    public List<PetiscoResponseDTO> findByNome(String nome);
    public List<PetiscoResponseDTO> findByAnimal(String animal);
    public List<PetiscoResponseDTO> findByDescricao(String descricao);
    public List<PetiscoResponseDTO> findByIdMarca(Long idMarca);
    public List<PetiscoResponseDTO> findByPesoProduto(Integer idPesoProduto);
    public List<PetiscoResponseDTO> findBySabor(Long idSabor);
}
