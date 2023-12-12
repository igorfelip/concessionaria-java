package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.exception.BadRequestException;
import com.projeto.concessionaria.mapper.ClienteMapper;
import com.projeto.concessionaria.repository.ClientesRepository;
import com.projeto.concessionaria.requests.ClientesPostRequestBody;
import com.projeto.concessionaria.requests.ClientesPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClientesService {
    private final ClientesRepository clientesRepository;

    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    public Clientes findById(Long id) {
        return clientesRepository.findById(id).orElseThrow(() ->
                (new BadRequestException("Cliente não encontrado")));
    }

    public Clientes save(ClientesPostRequestBody clientesPostRequestBody) {
        return clientesRepository.save(ClienteMapper.INSTANCE.toCliente(clientesPostRequestBody));
    }

    public void update(ClientesPutRequestBody clientesPutRequestBody) {
        Clientes byId = findById(clientesPutRequestBody.getId());
        Clientes clientes = ClienteMapper.INSTANCE.toCliente(clientesPutRequestBody);
        clientes.setId(byId.getId());
        clientesRepository.save(clientes);
    }

    public void delete(Long id) {
        Clientes byId = findById(id);
        clientesRepository.delete(byId);

    }
}

