package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import br.unitins.topicos1.util.Error;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoBrinquedo {
    BOLA(1, "Bola"),
    OSSO(2, "Osso"),
    CORDA(3, "Corda"),
    PELÚCIA(4, "Pelúcia"),
    ROEDOR(5, "Brinquedo roedor"),
    INTERATIVO(6, "Brinquedo interativo"),
    PUXAR(7, "Brinquedo para puxar"),
    ARRANHADOR(8, "Arranhador");

    private int id;
    private String nome;

    TipoBrinquedo(int id, String nome) {
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
    public static TipoBrinquedo valueOf(Integer id) throws IllegalArgumentException {
        for (TipoBrinquedo tipoBrinquedo : TipoBrinquedo.values()) {
            if (tipoBrinquedo.id == id)
                return tipoBrinquedo;
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error("404", "Brinquedo não encontrada para o ID fornecido: " + id))
                    .build());
    }
}
