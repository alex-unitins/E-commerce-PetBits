package br.unitins.topicos1.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido extends DefaultEntity {

    private LocalDateTime data;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_racao")
    private List<RacaoPedido> racao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_brinquedo")
    private List<BrinquedoPedido> brinquedo;

    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_remedio")
    private List<RemedioPedido> remedio;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_petisco")
    private List<PetiscoPedido> petisco;
    
    public List<RacaoPedido> getRacao() {
        return racao;
    }

    public void setRacao(List<RacaoPedido> racao) {
        this.racao = racao;
    }

    public List<BrinquedoPedido> getBrinquedo() {
        return brinquedo;
    }

    public void setBrinquedo(List<BrinquedoPedido> brinquedo) {
        this.brinquedo = brinquedo;
    }

    public List<RemedioPedido> getRemedio() {
        return remedio;
    }

    public void setRemedio(List<RemedioPedido> remedio) {
        this.remedio = remedio;
    }

    public List<PetiscoPedido> getPetisco() {
        return petisco;
    }

    public void setPetisco(List<PetiscoPedido> petisco) {
        this.petisco = petisco;
    }
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<RacaoPedido> getItens() {
        return racao;
    }

    public void setItens(List<RacaoPedido> racao) {
        this.racao = racao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}