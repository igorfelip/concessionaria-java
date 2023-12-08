package com.projeto.concessionaria.mapper;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.requests.ClientesPostRequestBody;
import com.projeto.concessionaria.requests.ClientesPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {
    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    public abstract Clientes toCliente(ClientesPostRequestBody clientesPostRequestBody);

    public abstract Clientes toCliente(ClientesPutRequestBody clientesPutRequestBody);
}
