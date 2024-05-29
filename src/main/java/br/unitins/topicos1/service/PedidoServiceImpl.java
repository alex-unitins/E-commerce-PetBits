package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.BrinquedoPedidoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.dto.RacaoPedidoDTO;
import br.unitins.topicos1.model.BrinquedoPedido;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.RacaoPedido;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public ClienteRepository clienteRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {

        Pedido pedido = new Pedido();
        double total =0;
        pedido.setData(LocalDateTime.now());
        pedido.setCliente(clienteRepository.findById(dto.idCliente()));
        // total calculado
        List<RacaoPedido> racao = new ArrayList<RacaoPedido>();

        for (RacaoPedidoDTO racaoDTO : dto.racao()) {
            RacaoPedido racaoPedido = new RacaoPedido();
            racaoPedido.setDesconto(racaoDTO.desconto());
            racaoPedido.setPreco(racaoDTO.preco());
            total += racaoDTO.preco();
            racao.add(racaoPedido);
        }

        List<BrinquedoPedido> brinquedo = new ArrayList<BrinquedoPedido>();

        for (BrinquedoPedidoDTO brinquedoDTO : dto.brinquedo()) {
            BrinquedoPedido brinquedoPedido = new BrinquedoPedido();
            brinquedoPedido.setDesconto(brinquedoDTO.desconto());
            brinquedoPedido.setPreco(brinquedoDTO.preco());
            total += brinquedoDTO.preco();
            brinquedo.add(brinquedoPedido);
        }

        pedido.setRacao(racao);
        pedido.setBrinquedo(brinquedo);

        pedidoRepository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }



    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido != null)
            return PedidoResponseDTO.valueOf(pedido);
        return null;
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository
        .listAll()
        .stream()
        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByCliente(Long idCliente) {
        return pedidoRepository.findByCliente(idCliente).stream()
        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

}