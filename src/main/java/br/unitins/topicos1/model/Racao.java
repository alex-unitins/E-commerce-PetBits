package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Racao extends Produto {

    @ManyToOne
    @JoinColumn(name = "id_sabor")
    private Sabor sabor;

    @Column(length = 60, nullable = false)
    private Idade idade;

    @Column(length = 60, nullable = false)
    private PesoProduto pesoProduto;

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Idade getIdade() {
        return idade;
    }

    public void setIdade(Idade idade) {
        this.idade = idade;
    }

    public PesoProduto getPesoProduto() {
        return pesoProduto;
    }

    public void setPesoProduto(PesoProduto pesoProduto) {
        this.pesoProduto = pesoProduto;
    }


    

}
