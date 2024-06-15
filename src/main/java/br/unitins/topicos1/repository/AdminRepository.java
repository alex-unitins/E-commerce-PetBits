package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Admin;
import br.unitins.topicos1.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepository implements PanacheRepository<Admin> {

    public List<Admin> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }
    public Admin findByUsername(String username){
        return find("usuario.username = ?1", username).firstResult();
    }

    public Admin findByUsernameAndSenha(String username, String senha) {
        return find("usuario.username = ?1 AND usuario.senha = ?2", username, senha).firstResult();
    }

}