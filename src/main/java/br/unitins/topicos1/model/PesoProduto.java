package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.unitins.topicos1.util.Error;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PesoProduto {
    CEM_GRAMAS(1, "100g"),
    DUZENTAS_GRAMAS(2, "200g"),
    TREZENTAS_GRAMAS(3, "300g"),
    QUATROCENTAS_GRAMAS(4, "400g"),
    QUINHENTAS_GRAMAS(5, "500g"),
    UM_KG(6, "1 kg"),
    DOIS_KG(7, "2 kg"),
    TRES_KG(8, "3 kg"),
    CINCO_KG(9, "5 kg"),
    DEZ_KG(10, "10 kg");

    private int id;
    private String descricao;

    PesoProduto(int id, String descricao) {
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
    public static PesoProduto valueOf(Integer id) throws IllegalArgumentException {
        for (PesoProduto pesoProduto : PesoProduto.values()) {
            if (pesoProduto.id == id)
                return pesoProduto;
        }
        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Error("400", "id de Peso do produto inválido: " + id))
                    .build());
    }
}
