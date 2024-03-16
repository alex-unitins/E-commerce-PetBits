package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public List<Produto> findByNome(String descricao) {
        return find("UPPER(descricao) LIKE ?1", "%" + descricao.toUpperCase() + "%").list();
    }
    
}
