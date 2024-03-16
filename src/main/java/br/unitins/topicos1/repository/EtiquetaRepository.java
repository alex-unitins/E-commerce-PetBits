package br.unitins.topicos1.repository;
import java.util.List;
import br.unitins.topicos1.model.Etiqueta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EtiquetaRepository implements PanacheRepository<Etiqueta> {

    public List<Etiqueta> findByNome(String nome) {
        return list("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%");
    }
}
