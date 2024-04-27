package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Idade;
import br.unitins.topicos1.model.PesoProduto;
import br.unitins.topicos1.model.Racao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RacaoRepository implements PanacheRepository<Racao> {

    public List<Racao> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

    public List<Racao> findByAnimal(String animal) {
        return find("UPPER(animal) LIKE ?1", "%"+ animal.toUpperCase() + "%").list();
    }

    public List<Racao> findByDescricao(String descricao) {
        return find("UPPER(descricao) Like ?1", "%"+ descricao.toUpperCase()+"%" ).list();
    }
    public List<Racao> findByIdMarca(Long idMarca) {
        return find("marca.id = ?1", idMarca).list();
    }
    public List<Racao> findByPesoProduto(PesoProduto pesoProduto){
        return find("pesoProduto =?1", pesoProduto).list();
    }
    public List<Racao> findByIdade(Idade idade){
        return find ("idade =?1", idade).list();
    }
    public List<Racao> findBySabor (Long idSabor){
        return find ("sabor.id = ?1", idSabor).list();
    }
    

    
}
