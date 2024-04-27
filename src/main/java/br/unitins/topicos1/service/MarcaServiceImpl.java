package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import br.unitins.topicos1.util.Error;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    @Inject
    public MarcaRepository marcaRepository;

    @Override
    @Transactional
    public MarcaResponseDTO create(@Valid MarcaDTO dto) {
        validarNomeMarca(dto.nome());
        validarCnpjMarca(dto.cnpj());

        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setCnpj(dto.cnpj());
        marca.setEmailContato(dto.emailContato());
        

        marcaRepository.persist(marca);
        return MarcaResponseDTO.valueOf(marca);
    }

    @SuppressWarnings("resource")
    public void validarId(long id){
        if (marcaRepository.findById(id)== null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "Marca não encontrada para o ID fornecido: " + id))
                    .build());
        }
    }

    public void validarNomeMarca(String nome) {
        Marca marca = marcaRepository.findByNomeCompleto(nome);
        if (marca != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    public void validarCnpjMarca(String cnpj) {
        Marca marca = marcaRepository.findByNomeCompleto(cnpj);
        if (marca != null)
            throw  new ValidationException("cnpj", "O cnpj '"+cnpj+"' já existe.");
    }

    @Override
    @Transactional
    public void update(Long id, MarcaDTO dto) {
        validarId(id);
        Marca marcaBanco =  marcaRepository.findById(id);

        marcaBanco.setNome(dto.nome());
        marcaBanco.setCnpj(dto.cnpj());
        marcaBanco.setEmailContato(dto.emailContato());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarId(id);
        marcaRepository.deleteById(id);
    }

    @Override
    public MarcaResponseDTO findById(Long id) {
        Marca marca = marcaRepository.findById(id);
         if (marca == null) {
            return null; 
         }
        return MarcaResponseDTO.valueOf(marcaRepository.findById(id));
    }

    @Override
    public List<MarcaResponseDTO> findAll() {
        return marcaRepository
        .listAll()
        .stream()
        .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MarcaResponseDTO> findByNome(String nome) {
        return marcaRepository.findByNome(nome).stream()
        .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }
   
}
