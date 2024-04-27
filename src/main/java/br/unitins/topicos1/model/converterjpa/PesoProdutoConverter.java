package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.PesoProduto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PesoProdutoConverter implements AttributeConverter<PesoProduto, Integer>{

    @Override
    public Integer convertToDatabaseColumn(PesoProduto pesoProduto) {
       return pesoProduto.getId();
    }

    @Override
    public PesoProduto convertToEntityAttribute(Integer id) {
        return PesoProduto.valueOf(id);
    }

    
}