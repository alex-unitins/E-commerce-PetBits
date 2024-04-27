package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.PesoAnimal;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PesoAnimalConverter implements AttributeConverter<PesoAnimal, Integer>{

    @Override
    public Integer convertToDatabaseColumn(PesoAnimal pesoAnimal) {
       return pesoAnimal.getId();
    }

    @Override
    public PesoAnimal convertToEntityAttribute(Integer id) {
        return PesoAnimal.valueOf(id);
    }

    
}