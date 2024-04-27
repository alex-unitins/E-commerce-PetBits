package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Marca;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {

    public List<Marca> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

    public Marca findByCnpj(String cnpj){
        return find("UPPER(cnpj) = ?1", cnpj.toUpperCase()).firstResult();
    }
    public Marca findByNomeCompleto(String nome) {
        return find("UPPER(nome) = ?1",  nome.toUpperCase() ).firstResult();
    }
    public Marca findByCnpjCompleto(String cnpj) {
        return find("UPPER(cnpj) = ?1",  cnpj.toUpperCase() ).firstResult();
    }

    
}
