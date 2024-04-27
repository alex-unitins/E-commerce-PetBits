package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Brinquedo;
import br.unitins.topicos1.model.TipoBrinquedo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrinquedoRepository implements PanacheRepository<Brinquedo> {

    public List<Brinquedo> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

    public List<Brinquedo> findByAnimal(String animal) {
        return find("UPPER(animal) LIKE ?1", "%"+ animal.toUpperCase() + "%").list();
    }

    public List<Brinquedo> findByDescricao(String descricao) {
        return find("UPPER(descricao) Like ?1", "%"+ descricao.toUpperCase()+"%" ).list();
    }
    public List<Brinquedo> findByIdMarca(Long idMarca) {
        return find("marca.id = ?1", idMarca).list();
    }
    public List<Brinquedo> findByTipoBrinquedo(TipoBrinquedo tipo){
        return find("tipoBrinquedo = ?1", tipo).list();
    }
 

    
}
