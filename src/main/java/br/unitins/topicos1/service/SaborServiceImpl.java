package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.SaborDTO;
import br.unitins.topicos1.dto.SaborResponseDTO;
import br.unitins.topicos1.model.Sabor;
import br.unitins.topicos1.repository.SaborRepository;
import br.unitins.topicos1.util.Error;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SaborServiceImpl implements SaborService {

    @Inject
    public SaborRepository saborRepository;

    @Override
    @Transactional
    public SaborResponseDTO create(@Valid SaborDTO dto) {
        validarNomeSabor(dto.nome());

        Sabor sabor = new Sabor();
        sabor.setNome(dto.nome());
        sabor.setIntensidade(dto.intensidade());

        saborRepository.persist(sabor);
        return SaborResponseDTO.valueOf(sabor);
    }

    public void validarNomeSabor(String nome) {
        Sabor sabor = saborRepository.findByNomeCompleto(nome);
        if (sabor != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }
    @SuppressWarnings("resource")
    public void validarId(long id){
        if (saborRepository.findById(id)== null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "sabor não encontrada para o ID fornecido: " + id))
                    .build());
        }
    }


    @Override
    @Transactional
    public void update(Long id, SaborDTO dto) {
        validarId(id);
        Sabor saborBanco =  saborRepository.findById(id);

        saborBanco.setNome(dto.nome());
        saborBanco.setIntensidade(dto.intensidade());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validarId(id);
        saborRepository.deleteById(id);
    }

    @Override
    public SaborResponseDTO findById(Long id) {
        Sabor sabor = saborRepository.findById(id);
        if (sabor == null) {
            return null; 
        }
        return SaborResponseDTO.valueOf(saborRepository.findById(id));
    }

    @Override
    public List<SaborResponseDTO> findAll() {
        return saborRepository
        .listAll()
        .stream()
        .map(e -> SaborResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<SaborResponseDTO> findByNome(String nome) {
        return saborRepository.findByNome(nome).stream()
        .map(e -> SaborResponseDTO.valueOf(e)).toList();
    }

   
}
