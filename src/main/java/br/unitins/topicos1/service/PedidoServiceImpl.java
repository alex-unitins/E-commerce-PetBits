package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.BrinquedoPedidoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.dto.PetiscoPedidoDTO;
import br.unitins.topicos1.dto.RacaoPedidoDTO;
import br.unitins.topicos1.dto.RemedioPedidoDTO;
import br.unitins.topicos1.model.BrinquedoPedido;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.PetiscoPedido;
import br.unitins.topicos1.model.RacaoPedido;
import br.unitins.topicos1.model.RemedioPedido;
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
        List<RacaoPedido> racao = new ArrayList<RacaoPedido>();

        for (RacaoPedidoDTO racaoDTO : dto.racao()) {
            if(racaoDTO.quantidade()>0){
                RacaoPedido racaoPedido = new RacaoPedido();
                racaoPedido.setDesconto(racaoDTO.desconto());
                racaoPedido.setPreco(racaoDTO.preco());
                total += racaoDTO.preco()*(racaoDTO.desconto()/100+1)*racaoDTO.quantidade();
                
            }
        }

        List<BrinquedoPedido> brinquedo = new ArrayList<BrinquedoPedido>();

        for (BrinquedoPedidoDTO brinquedoDTO : dto.brinquedo()) {
            if(brinquedoDTO.quantidade()>0){
            BrinquedoPedido brinquedoPedido = new BrinquedoPedido();
            brinquedoPedido.setDesconto(brinquedoDTO.desconto());
            brinquedoPedido.setPreco(brinquedoDTO.preco());
            total += brinquedoDTO.preco()*(brinquedoDTO.desconto()/100+1)*brinquedoDTO.quantidade();
            
            }
        }
        List<PetiscoPedido> petisco = new ArrayList<PetiscoPedido>();

        for (PetiscoPedidoDTO petiscoDTO : dto.petisco()) {
            if(petiscoDTO.quantidade()>0){
            PetiscoPedido petiscoPedido = new PetiscoPedido();
            petiscoPedido.setDesconto(petiscoDTO.desconto());
            petiscoPedido.setPreco(petiscoDTO.preco());
            total += petiscoDTO.preco()*(petiscoDTO.desconto()/100+1)*petiscoDTO.quantidade();
            
            }
        }
        List<RemedioPedido> remedio = new ArrayList<RemedioPedido>();

        for (RemedioPedidoDTO remedioDTO : dto.remedio()) {
            if(remedioDTO.quantidade()>0){
            RemedioPedido remedioPedido = new RemedioPedido();
            remedioPedido.setDesconto(remedioDTO.desconto());
            remedioPedido.setPreco(remedioDTO.preco());
            total += remedioDTO.preco()*(remedioDTO.desconto()/100+1)*(remedioDTO.quantidade());
            
            }
        }
        pedido.setTotal(total);
        pedido.setRemedio(remedio);
        pedido.setPetisco(petisco);
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