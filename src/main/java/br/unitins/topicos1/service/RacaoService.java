package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.RacaoDTO;
import br.unitins.topicos1.dto.RacaoResponseDTO;
import jakarta.validation.Valid;

public interface RacaoService {

    public RacaoResponseDTO create(@Valid RacaoDTO dto);
    public void update(Long id, RacaoDTO dto);
    public void delete(Long id);
    public RacaoResponseDTO findById(Long id);
    public List<RacaoResponseDTO> findAll();
    public List<RacaoResponseDTO> findByNome(String nome);
    public List<RacaoResponseDTO> findByAnimal(String animal);
    public List<RacaoResponseDTO> findByDescricao(String descricao);
    public List<RacaoResponseDTO> findByIdMarca(Long idMarca);
    public List<RacaoResponseDTO> findByPesoProduto(Integer idPesoProduto);
    public List<RacaoResponseDTO> findByIdade(Integer idade);
    public List<RacaoResponseDTO> findBySabor(Long idSabor);
}
