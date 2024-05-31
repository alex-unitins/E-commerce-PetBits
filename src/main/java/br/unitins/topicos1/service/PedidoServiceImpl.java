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
import br.unitins.topicos1.model.Brinquedo;
import br.unitins.topicos1.model.BrinquedoPedido;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.Petisco;
import br.unitins.topicos1.model.PetiscoPedido;
import br.unitins.topicos1.model.Racao;
import br.unitins.topicos1.model.RacaoPedido;
import br.unitins.topicos1.model.Remedio;
import br.unitins.topicos1.model.RemedioPedido;
import br.unitins.topicos1.repository.BrinquedoPedidoRepository;
import br.unitins.topicos1.repository.BrinquedoRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.PetiscoPedidoRepository;
import br.unitins.topicos1.repository.PetiscoRepository;
import br.unitins.topicos1.repository.RacaoPedidoRepository;
import br.unitins.topicos1.repository.RacaoRepository;
import br.unitins.topicos1.repository.RemedioPedidoRepository;
import br.unitins.topicos1.repository.RemedioRepository;
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

    @Inject
    public BrinquedoRepository brinquedoRepository;
    @Inject
    public RacaoRepository racaoRepository;
    @Inject
    public PetiscoRepository petiscoRepository;
    @Inject
    public RemedioRepository remedioRepository;
    @Inject
    public BrinquedoPedidoRepository brinquedoPedidoRepository;
    @Inject
    public PetiscoPedidoRepository petiscoPedidoRepository;
    @Inject
    public RemedioPedidoRepository remedioPedidoRepository;
    @Inject
    public RacaoPedidoRepository racaoPedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {

        Pedido pedido = new Pedido();
        double total =0;
        pedido.setData(LocalDateTime.now());
        pedido.setCliente(clienteRepository.findById(dto.idCliente()));
        List<RacaoPedido> racao = new ArrayList<RacaoPedido>();

        for (RacaoPedidoDTO racaoDTO : dto.racao()) {
            Racao racaoBanco = racaoRepository.findById(racaoDTO.idRacao());
            if(racaoDTO.quantidade()>0 && racaoDTO.quantidade() <= racaoBanco.getEstoque()){
                RacaoPedido racaoPedido = new RacaoPedido();
                racaoPedido.setDesconto(racaoDTO.desconto());
                racaoPedido.setPreco(racaoBanco.getPreco());
                racaoPedido.setRacao(racaoBanco);
                racaoPedido.setQuantidade(racaoDTO.quantidade());
                total += racaoPedido.getPreco()/(racaoDTO.desconto()/100+1)*racaoDTO.quantidade();
                racaoBanco.setEstoque(racaoBanco.getEstoque()-racaoDTO.quantidade());
                racaoPedidoRepository.persist(racaoPedido);
                racao.add(racaoPedido);
            }
        }
        

        List<BrinquedoPedido> brinquedo = new ArrayList<BrinquedoPedido>();

        for (BrinquedoPedidoDTO brinquedoDTO : dto.brinquedo()) {
            Brinquedo brinquedoBanco = brinquedoRepository.findById(brinquedoDTO.idBrinquedo());
            if(brinquedoDTO.quantidade()>0 && brinquedoDTO.quantidade()<=brinquedoBanco.getEstoque()){
                BrinquedoPedido brinquedoPedido = new BrinquedoPedido();
                brinquedoPedido.setDesconto(brinquedoDTO.desconto());
                brinquedoPedido.setPreco(brinquedoBanco.getPreco());
                brinquedoPedido.setQuantidade(brinquedoDTO.quantidade());
                total += brinquedoPedido.getPreco()/(brinquedoDTO.desconto()/100+1)*brinquedoDTO.quantidade();
                brinquedoBanco.setEstoque(brinquedoBanco.getEstoque()-brinquedoDTO.quantidade());
                brinquedoPedido.setBrinquedo(brinquedoBanco);
                brinquedoPedidoRepository.persist(brinquedoPedido);
                brinquedo.add(brinquedoPedido);
            }
        }
        List<PetiscoPedido> petisco = new ArrayList<PetiscoPedido>();

        for (PetiscoPedidoDTO petiscoDTO : dto.petisco()) {
            Petisco petiscoBanco = petiscoRepository.findById(petiscoDTO.idPetisco());
            if(petiscoDTO.quantidade()>0 && petiscoDTO.quantidade()<=petiscoBanco.getEstoque()){
                PetiscoPedido petiscoPedido = new PetiscoPedido();
                petiscoPedido.setDesconto(petiscoDTO.desconto());
                petiscoPedido.setPreco(petiscoBanco.getPreco());
                petiscoPedido.setPetisco(petiscoBanco);
                petiscoPedido.setQuantidade(petiscoDTO.quantidade());
                total += petiscoPedido.getPreco()/(petiscoDTO.desconto()/100+1)*petiscoDTO.quantidade();
                petiscoBanco.setEstoque(petiscoBanco.getEstoque()-petiscoDTO.quantidade());
                petiscoPedidoRepository.persist(petiscoPedido);
                petisco.add(petiscoPedido);
            }
        }
        List<RemedioPedido> remedio = new ArrayList<RemedioPedido>();

        for (RemedioPedidoDTO remedioDTO : dto.remedio()) {
            Remedio remedioBanco = remedioRepository.findById(remedioDTO.idRemedio());
            if(remedioDTO.quantidade()>0 && remedioDTO.quantidade()<=remedioBanco.getEstoque()){
                RemedioPedido remedioPedido = new RemedioPedido();
                remedioPedido.setDesconto(remedioDTO.desconto());
                remedioPedido.setPreco(remedioBanco.getPreco());
                remedioPedido.setRemedio(remedioBanco);
                remedioPedido.setQuantidade(remedioDTO.quantidade());
                total += remedioPedido.getPreco()/(remedioDTO.desconto()/100+1)*remedioDTO.quantidade();
                remedioBanco.setEstoque(remedioBanco.getEstoque()-remedioDTO.quantidade());
                remedioPedidoRepository.persist(remedioPedido);
                remedio.add(remedioPedido);
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