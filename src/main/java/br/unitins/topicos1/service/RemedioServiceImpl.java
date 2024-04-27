package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.RemedioDTO;
import br.unitins.topicos1.dto.RemedioResponseDTO;
import br.unitins.topicos1.model.Idade;
import br.unitins.topicos1.model.PesoAnimal;
import br.unitins.topicos1.model.Remedio;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.RemedioRepository;
import br.unitins.topicos1.util.Error;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RemedioServiceImpl implements RemedioService {

    @Inject
    public RemedioRepository remedioRepository;

    @Inject
    public MarcaRepository marcaRepository;


    @Override
    @Transactional
    public RemedioResponseDTO create(@Valid RemedioDTO dto) {
        validarMarca(dto.idMarca());
        validarPesoAnimal(dto.idPesoAnimal());
        validarIdade(dto.idIdade());
        Remedio remedio = new Remedio();
        remedio.setNome(dto.nome());
        remedio.setPreco(dto.preco());
        remedio.setQuantidade(dto.quantidade());
        remedio.setDescricao(dto.descricao());
        remedio.setMarca(marcaRepository.findById(dto.idMarca()));
        remedio.setAnimal(dto.animal());
        remedio.setPesoAnimal(PesoAnimal.valueOf(dto.idPesoAnimal()));
        remedio.setIdade(Idade.valueOf(dto.idIdade()));


        remedioRepository.persist(remedio);
        return RemedioResponseDTO.valueOf(remedio);
    }
    @SuppressWarnings("resource")
    public void validarId(long id){
        if (remedioRepository.findById(id)== null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "Remédio não encontrada para o ID fornecido: " + id))
                    .build());
        }
    }
    @SuppressWarnings("resource")
    public void validarMarca(long idMarca) {
        if (remedioRepository.findById(idMarca) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "Marca não encontrada para o ID fornecido: " + idMarca))
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
    }@SuppressWarnings("resource")
    public void validarPesoAnimal(Integer pesoAnimal){
        if (PesoAnimal.valueOf(pesoAnimal)==null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Error("400", "id de Peso do animal inválido: " + pesoAnimal))
                    .build());
        }
    }


    @Override
    @Transactional
    public void update(Long id, RemedioDTO dto) {
        Remedio remedioBanco = remedioRepository.findById(id);
        validarId(id);
        validarMarca(dto.idMarca());
        validarPesoAnimal(dto.idPesoAnimal());
        validarIdade(dto.idIdade());
        remedioBanco.setNome(dto.nome());
        remedioBanco.setPreco(dto.preco());
        remedioBanco.setQuantidade(dto.quantidade());
        remedioBanco.setDescricao(dto.descricao());
        remedioBanco.setMarca(marcaRepository.findById(dto.idMarca()));
        remedioBanco.setAnimal(dto.animal());
        remedioBanco.setPesoAnimal(PesoAnimal.valueOf(dto.idPesoAnimal()));
        remedioBanco.setIdade(Idade.valueOf(dto.idIdade()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarId(id);
        remedioRepository.deleteById(id);
    }

    @Override
    public RemedioResponseDTO findById(Long id) {
        Remedio remedio = remedioRepository.findById(id);
        if (remedio == null) {
            return null; 
        }
        return RemedioResponseDTO.valueOf(remedioRepository.findById(id));
    }

    @Override
    public List<RemedioResponseDTO> findAll() {
        return remedioRepository.listAll().stream()
        .map(e -> RemedioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<RemedioResponseDTO> findByNome(String nome) {
        return remedioRepository.findByNome(nome).stream()
                .map(e -> RemedioResponseDTO.valueOf(e)).toList();
    }


    @Override
    public List<RemedioResponseDTO> findByAnimal(String animal) {
        return remedioRepository.findByAnimal(animal).stream()
                .map(e -> RemedioResponseDTO.valueOf(e)).toList();
    }


    @Override
    public List<RemedioResponseDTO> findByDescricao(String descricao) {
        return remedioRepository.findByDescricao(descricao).stream()
                .map(e -> RemedioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<RemedioResponseDTO> findByIdMarca(Long idMarca) {
        validarMarca(idMarca);
        return remedioRepository.findByIdMarca(idMarca).stream()
                .map(e -> RemedioResponseDTO.valueOf(e)).toList();
    }
    public List<RemedioResponseDTO> findByIdade(Integer idade){
        validarIdade(idade);
        return remedioRepository.findByIdade(Idade.valueOf(idade)).stream().map(e->RemedioResponseDTO.valueOf(e)).toList();
    }
    public List<RemedioResponseDTO> findByPesoAnimal(Integer pesoAnimal){
        validarPesoAnimal(pesoAnimal);
        return remedioRepository.findByPesoAnimal(PesoAnimal.valueOf(pesoAnimal)).stream().map(e->RemedioResponseDTO.valueOf(e)).toList();
    }
    

}
