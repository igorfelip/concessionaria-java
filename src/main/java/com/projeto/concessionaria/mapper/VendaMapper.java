package com.projeto.concessionaria.mapper;

import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.requests.VendasPostRequestBody;
import com.projeto.concessionaria.requests.VendasPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class VendaMapper {
    public static final VendaMapper INSTANCE = Mappers.getMapper(VendaMapper.class);

    public abstract Vendas toVenda(VendasPostRequestBody vendasPostRequestBody);

    public abstract Vendas toVendas(VendasPutRequestBody vendasPutRequestBody);
}
