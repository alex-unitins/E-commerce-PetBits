package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.RacaoDTO;
import br.unitins.topicos1.dto.RacaoResponseDTO;
import br.unitins.topicos1.model.Idade;
import br.unitins.topicos1.model.PesoProduto;
import br.unitins.topicos1.model.Racao;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.RacaoRepository;
import br.unitins.topicos1.repository.SaborRepository;
import br.unitins.topicos1.util.Error;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RacaoServiceImpl implements RacaoService {

    @Inject
    public RacaoRepository racaoRepository;

    @Inject
    public MarcaRepository marcaRepository;

    @Inject
    public SaborRepository saborRepository;

    @Override
    @Transactional
    public RacaoResponseDTO create(@Valid RacaoDTO dto) {
        validarMarca(dto.idMarca());
        validarPesoProduto(dto.idPesoProduto());
        validarSabor(dto.idSabor());
        validarIdade(dto.idIdade());
        Racao racao = new Racao();
        racao.setNome(dto.nome());
        racao.setPreco(dto.preco());
        racao.setDescricao(dto.descricao());
        racao.setMarca(marcaRepository.findById(dto.idMarca()));
        racao.setAnimal(dto.animal());
        racao.setSabor(saborRepository.findById(dto.idSabor()));
        racao.setPesoProduto(PesoProduto.valueOf(dto.idPesoProduto()));
        racao.setIdade(Idade.valueOf(dto.idIdade()));
        racao.setEstoque(dto.estoque());

        racaoRepository.persist(racao);
        return RacaoResponseDTO.valueOf(racao);
    }

    @SuppressWarnings("resource")
    public void validarId(long id){
        if (racaoRepository.findById(id)== null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "ração não encontrada para o ID fornecido: " + id))
                    .build());
        }
    }
    @SuppressWarnings("resource")
    public void validarMarca(long idMarca) {
        if (marcaRepository.findById(idMarca) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "Marca não encontrada para o ID fornecido: " + idMarca))
                    .build());
        }
    }
    @SuppressWarnings("resource")
    public void validarPesoProduto(Integer pesoProduto){
        if (PesoProduto.valueOf(pesoProduto)==null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Error("400", "id de Peso do produto inválido: " + pesoProduto))
                    .build());
        }
    }
    @SuppressWarnings("resource")
    public void validarSabor(long sabor){
        if (saborRepository.findById(sabor)==null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "Sabor não encontrado para o ID fornecido: " + sabor))
                    .build());
        }
    }
    @SuppressWarnings("resource")
    public void validarIdade(Integer idade){
        if (Idade.valueOf(idade)==null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "idade não encontrada para o ID fornecido: " + idade))
                    .build());
        }
    }

    @Override
    @Transactional
    public void update(Long id, RacaoDTO dto) {
        validarId(id);
        validarMarca(dto.idMarca());
        validarPesoProduto(dto.idPesoProduto());
        validarSabor(dto.idSabor());
        validarIdade(dto.idIdade());
        Racao racaoBanco = racaoRepository.findById(id);

        racaoBanco.setEstoque(dto.estoque());
        racaoBanco.setNome(dto.nome());
        racaoBanco.setPreco(dto.preco());
        racaoBanco.setDescricao(dto.descricao());
        racaoBanco.setMarca(marcaRepository.findById(dto.idMarca()));
        racaoBanco.setAnimal(dto.animal());
        racaoBanco.setSabor(saborRepository.findById(dto.idSabor()));
        racaoBanco.setPesoProduto(PesoProduto.valueOf(dto.idPesoProduto()));
        racaoBanco.setIdade(Idade.valueOf(dto.idIdade()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarId(id);
        racaoRepository.deleteById(id);
    }

    @Override
    public RacaoResponseDTO findById(Long id) {
        Racao racao = racaoRepository.findById(id);
        if (racao == null) {
            return null; 
        }
        return RacaoResponseDTO.valueOf(racaoRepository.findById(id));
    }

    @Override
    public List<RacaoResponseDTO> findAll() {
        return racaoRepository
                .listAll()
                .stream()
                .map(e -> RacaoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<RacaoResponseDTO> findByNome(String nome) {
        return racaoRepository.findByNome(nome).stream()
                .map(e -> RacaoResponseDTO.valueOf(e)).toList();
    }


    @Override
    public List<RacaoResponseDTO> findByAnimal(String animal) {
        return racaoRepository.findByAnimal(animal).stream()
                .map(e -> RacaoResponseDTO.valueOf(e)).toList();
    }


    @Override
    public List<RacaoResponseDTO> findByDescricao(String descricao) {
        return racaoRepository.findByDescricao(descricao).stream()
                .map(e -> RacaoResponseDTO.valueOf(e)).toList();
    }
    
    @Override
    public List<RacaoResponseDTO> findByIdMarca(Long idMarca) {
        validarMarca(idMarca);
        return racaoRepository.findByIdMarca(idMarca).stream()
                .map(e -> RacaoResponseDTO.valueOf(e)).toList();
    }
    public List<RacaoResponseDTO> findByPesoProduto(Integer idPesoProduto){
        validarPesoProduto(idPesoProduto);
        return racaoRepository.findByPesoProduto(PesoProduto.valueOf(idPesoProduto)).stream()
                .map(e -> RacaoResponseDTO.valueOf(e)).toList();
    }
    public List<RacaoResponseDTO> findByIdade(Integer idade){
        validarIdade(idade);
        return racaoRepository.findByIdade(Idade.valueOf(idade)).stream().map(e->RacaoResponseDTO.valueOf(e)).toList();
    }
    public List<RacaoResponseDTO> findBySabor(Long idSabor){
        validarSabor(idSabor);
        return racaoRepository.findBySabor(idSabor).stream().map(e-> RacaoResponseDTO.valueOf(e)).toList();
    }
    

}
