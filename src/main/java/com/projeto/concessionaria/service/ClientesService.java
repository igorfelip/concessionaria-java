package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.exception.BadRequestException;
import com.projeto.concessionaria.mapper.ClienteMapper;
import com.projeto.concessionaria.repository.ClienteRepository;
import com.projeto.concessionaria.requests.ClientesPostRequestBody;
import com.projeto.concessionaria.requests.ClientesPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClientesService {
    private final ClienteRepository clienteRepository;

    public List<Clientes> findAll() {
        return clienteRepository.findAll();
    }

    public Clientes findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                (new BadRequestException("Cliente não encontrado")));
    }

    public Clientes save(ClientesPostRequestBody clientesPostRequestBody) {
        return clienteRepository.save(ClienteMapper.INSTANCE.toCliente(clientesPostRequestBody));
    }

    public void update(ClientesPutRequestBody clientesPutRequestBody) {
        Clientes byId = findById(clientesPutRequestBody.getId());
        Clientes clientes = ClienteMapper.INSTANCE.toCliente(clientesPutRequestBody);
        clientes.setId(byId.getId());
        clienteRepository.save(clientes);
    }

    public void delete(Long id) {
        Clientes byId = findById(id);
        clienteRepository.delete(byId);

    }
}

