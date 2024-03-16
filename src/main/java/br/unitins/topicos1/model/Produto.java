package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "etiqueta1_id", nullable = false)
    private Etiqueta etiqueta1;

    @ManyToOne
    @JoinColumn(name = "etiqueta2_id", nullable = true)
    private Etiqueta etiqueta2;

    @ManyToOne
    @JoinColumn(name = "etiqueta3_id", nullable = true)
    private Etiqueta etiqueta3;

    @Column(nullable = false)
    private Float preco;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Etiqueta getEtiqueta1() {
        return etiqueta1;
    }

    public void setEtiqueta1(Etiqueta etiqueta1) {
        this.etiqueta1 = etiqueta1;
    }

    public Etiqueta getEtiqueta2() {
        return etiqueta2;
    }

    public void setEtiqueta2(Etiqueta etiqueta2) {
        this.etiqueta2 = etiqueta2;
    }

    public Etiqueta getEtiqueta3() {
        return etiqueta3;
    }

    public void setEtiqueta3(Etiqueta etiqueta3) {
        this.etiqueta3 = etiqueta3;
    }
}
