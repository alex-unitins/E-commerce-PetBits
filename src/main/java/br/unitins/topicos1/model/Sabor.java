package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Sabor extends DefaultEntity {
    
    @Column(length = 60, nullable = false)
    private String nome;
    @Column(length = 60, nullable = false)
    private String intensidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }


}
