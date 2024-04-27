package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.TipoBrinquedo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoBrinquedoConverter implements AttributeConverter<TipoBrinquedo, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoBrinquedo tipoBrinquedo) {
       return tipoBrinquedo.getId();
    }

    @Override
    public TipoBrinquedo convertToEntityAttribute(Integer id) {
        return TipoBrinquedo.valueOf(id);
    }

    
}