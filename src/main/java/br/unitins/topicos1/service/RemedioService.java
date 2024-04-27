package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.RemedioDTO;
import br.unitins.topicos1.dto.RemedioResponseDTO;
import jakarta.validation.Valid;

public interface RemedioService {

    public RemedioResponseDTO create(@Valid RemedioDTO dto);
    public void update(Long id, RemedioDTO dto);
    public void delete(Long id);
    public RemedioResponseDTO findById(Long id);
    public List<RemedioResponseDTO> findAll();
    public List<RemedioResponseDTO> findByNome(String nome);
    public List<RemedioResponseDTO> findByAnimal(String animal);
    public List<RemedioResponseDTO> findByDescricao(String descricao);
    public List<RemedioResponseDTO> findByIdMarca(Long idMarca);
    public List<RemedioResponseDTO> findByIdade(Integer idade);
    public List<RemedioResponseDTO> findByPesoAnimal(Integer pesoAnimal);
}
