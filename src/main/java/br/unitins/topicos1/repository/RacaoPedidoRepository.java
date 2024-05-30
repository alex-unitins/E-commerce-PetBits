package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.RacaoPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RacaoPedidoRepository implements PanacheRepository<RacaoPedido> {

    public List<RacaoPedido> findByCliente(Long idCliente) {
        return find("UPPER(cliente.id) = ?1", idCliente).list();
    }
    
}