package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Remedio extends Produto {

    @Column(length = 60, nullable = false)
    private String quantidade;
    
    @Column(length = 60, nullable = false)
    private Idade idade;

    @Column(length = 60, nullable = false)
    private PesoAnimal pesoAnimal;

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Idade getIdade() {
        return idade;
    }

    public void setIdade(Idade idade) {
        this.idade = idade;
    }

    public PesoAnimal getPesoAnimal() {
        return pesoAnimal;
    }

    public void setPesoAnimal(PesoAnimal pesoAnimal) {
        this.pesoAnimal = pesoAnimal;
    }

}
