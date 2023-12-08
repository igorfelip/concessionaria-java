package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.requests.ClientesPostRequestBody;
import com.projeto.concessionaria.requests.ClientesPutRequestBody;
import com.projeto.concessionaria.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("clientes")
public class ClienteController {
    private final ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<Clientes>> findAll() {
        return new ResponseEntity<>(clientesService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Clientes> findById(@PathVariable Long id) {
        return new ResponseEntity<>(clientesService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Clientes> save(@RequestBody @Valid ClientesPostRequestBody clientesPostRequestBody) {
        return new ResponseEntity<>(clientesService.save(clientesPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ClientesPutRequestBody clientesPutRequestBody) {
        clientesService.update(clientesPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        clientesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

