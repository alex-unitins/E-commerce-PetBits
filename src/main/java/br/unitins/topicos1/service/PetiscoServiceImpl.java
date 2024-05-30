package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PetiscoDTO;
import br.unitins.topicos1.dto.PetiscoResponseDTO;
import br.unitins.topicos1.model.PesoProduto;
import br.unitins.topicos1.model.Petisco;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.PetiscoRepository;
import br.unitins.topicos1.repository.SaborRepository;
import br.unitins.topicos1.util.Error;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PetiscoServiceImpl implements PetiscoService {

    @Inject
    public PetiscoRepository petiscoRepository;

    @Inject
    public MarcaRepository marcaRepository;

    @Inject
    public SaborRepository saborRepository;

    @Override
    @Transactional
    public PetiscoResponseDTO create(@Valid PetiscoDTO dto) {
        validarMarca(dto.idMarca());
        validarPesoProduto(dto.idPesoProduto());
        validarSabor(dto.idSabor());
        Petisco petisco = new Petisco();
        petisco.setNome(dto.nome());
        petisco.setPreco(dto.preco());
        petisco.setUnidades(dto.unidades());
        petisco.setDescricao(dto.descricao());
        petisco.setMarca(marcaRepository.findById(dto.idMarca()));
        petisco.setAnimal(dto.animal());
        petisco.setSabor(saborRepository.findById(dto.idSabor()));
        petisco.setEstoque(dto.estoque());
        petisco.setPesoProduto(PesoProduto.valueOf(dto.idPesoProduto()));


        petiscoRepository.persist(petisco);
        return PetiscoResponseDTO.valueOf(petisco);
    }

    @SuppressWarnings("resource")
    public void validarId(long id){
        if (petiscoRepository.findById(id)== null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "petisco não encontrada para o ID fornecido: " + id))
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

    @Override
    @Transactional
    public void update(Long id, PetiscoDTO dto) {
        validarId(id);
        validarMarca(dto.idMarca());
        validarSabor(dto.idSabor());
        validarPesoProduto(dto.idPesoProduto());
        Petisco petiscoBanco = petiscoRepository.findById(id);

        petiscoBanco.setNome(dto.nome());
        petiscoBanco.setPreco(dto.preco());
        petiscoBanco.setUnidades(dto.unidades());
        petiscoBanco.setDescricao(dto.descricao());
        petiscoBanco.setMarca(marcaRepository.findById(dto.idMarca()));
        petiscoBanco.setAnimal(dto.animal());
        petiscoBanco.setSabor(saborRepository.findById(dto.idSabor()));
        petiscoBanco.setPesoProduto(PesoProduto.valueOf(dto.idPesoProduto()));
        petiscoBanco.setEstoque(dto.estoque());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarId(id);
        petiscoRepository.deleteById(id);
    }

    @Override
    public PetiscoResponseDTO findById(Long id) {
        Petisco petisco = petiscoRepository.findById(id);
        if (petisco == null) {
            return null; 
        }
        return PetiscoResponseDTO.valueOf(petiscoRepository.findById(id));
    }

    @Override
    public List<PetiscoResponseDTO> findAll() {
        return petiscoRepository
                .listAll()
                .stream()
                .map(e -> PetiscoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PetiscoResponseDTO> findByNome(String nome) {
        return petiscoRepository.findByNome(nome).stream()
                .map(e -> PetiscoResponseDTO.valueOf(e)).toList();
    }


    @Override
    public List<PetiscoResponseDTO> findByAnimal(String animal) {
        return petiscoRepository.findByAnimal(animal).stream()
                .map(e -> PetiscoResponseDTO.valueOf(e)).toList();
    }


    @Override
    public List<PetiscoResponseDTO> findByDescricao(String descricao) {
        return petiscoRepository.findByDescricao(descricao).stream()
                .map(e -> PetiscoResponseDTO.valueOf(e)).toList();
    }
    @Override
    public List<PetiscoResponseDTO> findByIdMarca(Long idMarca) {
        validarMarca(idMarca);
        return petiscoRepository.findByIdMarca(idMarca).stream()
                .map(e -> PetiscoResponseDTO.valueOf(e)).toList();
    }
    public List<PetiscoResponseDTO> findByPesoProduto(Integer idPesoProduto){
        validarPesoProduto(idPesoProduto);
        return petiscoRepository.findByPesoProduto(PesoProduto.valueOf(idPesoProduto)).stream()
                .map(e -> PetiscoResponseDTO.valueOf(e)).toList();
    }
    public List<PetiscoResponseDTO> findBySabor(Long idSabor){
        validarSabor(idSabor);
        return petiscoRepository.findBySabor(idSabor).stream().map(e-> PetiscoResponseDTO.valueOf(e)).toList();
    }

}
