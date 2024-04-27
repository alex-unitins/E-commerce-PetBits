package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Brinquedo extends Produto {

    @Column(length = 60, nullable = false)
    private String material;

    @Column(length = 60, nullable = false)
    private TipoBrinquedo tipoBrinquedo;

    public TipoBrinquedo getTipoBrinquedo() {
        return tipoBrinquedo;
    }

    public void setTipoBrinquedo(TipoBrinquedo tipoBrinquedo) {
        this.tipoBrinquedo = tipoBrinquedo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }


}
