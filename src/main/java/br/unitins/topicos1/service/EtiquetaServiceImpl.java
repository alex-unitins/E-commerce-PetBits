package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EtiquetaDTO;
import br.unitins.topicos1.dto.EtiquetaResponseDTO;
import br.unitins.topicos1.model.Etiqueta;
import br.unitins.topicos1.repository.EtiquetaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EtiquetaServiceImpl implements EtiquetaService {

    @Inject
    private EtiquetaRepository etiquetaRepository;

    @Override
    @Transactional
    public EtiquetaResponseDTO create(@Valid EtiquetaDTO dto) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNome(dto.nome());

        etiquetaRepository.persist(etiqueta);
        return EtiquetaResponseDTO.valueOf(etiqueta);
    }

    @Override
    @Transactional
    public void update(Long id,@Valid EtiquetaDTO dto) {
        Etiqueta etiquetaBanco = etiquetaRepository.findById(id);
        if (etiquetaBanco != null) {
            etiquetaBanco.setNome(dto.nome());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        etiquetaRepository.deleteById(id);
    }

    @Override
    public EtiquetaResponseDTO findById(Long id) {
        return EtiquetaResponseDTO.valueOf(etiquetaRepository.findById(id));
    }

    @Override
    public List<EtiquetaResponseDTO> findByNome(String nome) {
        return etiquetaRepository
            .findByNome(nome)
            .stream()
            .map(e -> EtiquetaResponseDTO.valueOf(e))
            .toList();
    }

    @Override
    public List<EtiquetaResponseDTO> findAll() {
        return etiquetaRepository.listAll().stream()
                .map(EtiquetaResponseDTO::valueOf)
                .toList();
    }
}
