package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Idade;
import br.unitins.topicos1.model.PesoAnimal;
import br.unitins.topicos1.model.Remedio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RemedioRepository implements PanacheRepository<Remedio> {

    public List<Remedio> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

    public List<Remedio> findByAnimal(String animal) {
        return find("UPPER(animal) LIKE ?1", "%"+ animal.toUpperCase() + "%").list();
    }

    public List<Remedio> findByDescricao(String descricao) {
        return find("UPPER(descricao) Like ?1", "%"+ descricao.toUpperCase()+"%" ).list();
    }
    public List<Remedio> findByPesoAnimal(PesoAnimal pesoAnimal){
        return find ("pesoAnimal =?1", pesoAnimal).list();
    }

    public List<Remedio> findByIdMarca(Long idMarca) {
        return find("marca.id = ?1", idMarca).list();
    }
    public List<Remedio> findByIdade(Idade idade){
        return find ("idade =?1", idade).list();
    }
    
}
