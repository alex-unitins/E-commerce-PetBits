package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.PesoProduto;
import br.unitins.topicos1.model.Petisco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetiscoRepository implements PanacheRepository<Petisco> {

    public List<Petisco> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

    public List<Petisco> findByAnimal(String animal) {
        return find("UPPER(animal) LIKE ?1", "%"+ animal.toUpperCase() + "%").list();
    }

    public List<Petisco> findByDescricao(String descricao) {
        return find("UPPER(descricao) Like ?1", "%"+ descricao.toUpperCase()+"%" ).list();
    }

    public List<Petisco> findByIdMarca(Long idMarca) {
        return find("marca.id = ?1", idMarca).list();
    }
    public List<Petisco> findByPesoProduto(PesoProduto pesoProduto){
        return find("pesoProduto =?1", pesoProduto).list();
    }
    public List<Petisco> findBySabor (Long idSabor){
        return find ("sabor.id = ?1", idSabor).list();
    }
    
}
