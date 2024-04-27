package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import br.unitins.topicos1.util.Error;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PesoAnimal {
    MUITOLEVE(1, "Até 2 kg"),
    LEVE(2, "De 2 a 6 kg"),
    MÉDIO(3, "De 6 a 15 kg"),
    PESADO(4, "De 15 a 30 kg"),
    MUITOPESADO(5, "Acima de 30 kg");

    private int id;
    private String descricao;

    PesoAnimal(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @SuppressWarnings("resource")
    public static PesoAnimal valueOf(Integer id) throws IllegalArgumentException {
        for (PesoAnimal faixa : PesoAnimal.values()) {
            if (faixa.id == id)
                return faixa;
        }
        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                .entity(new Error("400", "id de Peso do animal inválido: " + id))
                .build());

    }
}
