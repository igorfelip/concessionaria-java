package com.projeto.concessionaria.mapper;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.requests.CarrosPostRequestBody;
import com.projeto.concessionaria.requests.CarrosPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CarroMapper {
    public static final CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);

    public abstract Carros toCarro(CarrosPostRequestBody carrosPostRequestBody);

    public abstract Carros toCarro(CarrosPutRequestBody carrosPutRequestBody);
}
