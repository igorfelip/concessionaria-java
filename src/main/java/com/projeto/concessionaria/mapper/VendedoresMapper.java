package com.projeto.concessionaria.mapper;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.requests.VendedoresPostRequestBody;
import com.projeto.concessionaria.requests.VendedoresPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class VendedoresMapper {
    public static final VendedoresMapper INSTANCE = Mappers.getMapper(VendedoresMapper.class);

    public abstract Vendedores toVendedores(VendedoresPostRequestBody vendedoresPostRequestBody);

    public abstract Vendedores toVendedores(VendedoresPutRequestBody vendedoresPutRequestBody);
}
