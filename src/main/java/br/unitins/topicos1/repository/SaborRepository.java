package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Sabor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaborRepository implements PanacheRepository<Sabor> {

    public List<Sabor> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

    public List<Sabor> findByIntensidade(String intensidade){
        return find("UPPER(intensidade) LIKE ?1", "%"+intensidade.toUpperCase()+"%").list();
    }
    public Sabor findByNomeCompleto(String nome) {
        return find("UPPER(nome) = ?1",  nome.toUpperCase() ).firstResult();
    }


}
