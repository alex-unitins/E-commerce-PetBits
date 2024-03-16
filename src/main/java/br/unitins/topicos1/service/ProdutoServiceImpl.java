package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setDescricao(dto.descricao());
        produto.setMarca(dto.marca());
        produto.setEtiqueta1(dto.etiqueta1());
        produto.setEtiqueta2(dto.etiqueta2());
        produto.setEtiqueta3(dto.etiqueta3());
        produto.setPreco(dto.preco());

        produtoRepository.persist(produto);
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    @Transactional
    public void update(Long id,@Valid ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setDescricao(dto.descricao());
            produto.setMarca(dto.marca());
            produto.setEtiqueta1(dto.etiqueta1());
            produto.setEtiqueta2(dto.etiqueta2());
            produto.setEtiqueta3(dto.etiqueta3());
            produto.setPreco(dto.preco());

            produtoRepository.persist(produto);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id);
        return produto != null ? ProdutoResponseDTO.valueOf(produto) : null;
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String descricao) {
        return produtoRepository
            .findByNome(descricao)
            .stream()
            .map(e -> ProdutoResponseDTO.valueOf(e))
            .toList();
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository
                .listAll()
                .stream()
                .map(ProdutoResponseDTO::valueOf)
                .toList();
    }

}
