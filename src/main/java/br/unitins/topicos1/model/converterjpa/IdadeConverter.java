package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.Idade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IdadeConverter implements AttributeConverter<Idade, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Idade idade) {
       return idade.getId();
    }

    @Override
    public Idade convertToEntityAttribute(Integer id) {
        return Idade.valueOf(id);
    }

    
}