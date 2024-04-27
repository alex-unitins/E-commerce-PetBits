package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import br.unitins.topicos1.util.Error;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Idade {
    RECENTE_NASCIDO(1, "Recente Nascido"),
    FILHOTE(2, "Filhote"),
    JOVEM(3, "Jovem"),
    ADULTO(4, "Adulto"),
    IDOSO(5, "Sênior"); 

    private int id;
    private String nome;
    

    Idade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    @SuppressWarnings("resource")
    public static Idade valueOf(Integer id) throws IllegalArgumentException {
        for (Idade faixaIdade : Idade.values()) {
            if (faixaIdade.id == id)
                return faixaIdade;
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "idade não encontrada para o ID fornecido: " + id))
                    .build());
    }
}
