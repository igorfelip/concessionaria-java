package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.service.VendasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vendas")
public class VendasController {
    private final VendasService vendasService;

    @GetMapping
    public ResponseEntity<List<Vendas>> findAll() {
        return new ResponseEntity<>(vendasService.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Vendas> findById(@PathVariable Long id) {
        return new ResponseEntity<>(vendasService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/{parc}")
    public ResponseEntity<Vendas> save(@RequestBody Vendas venda, @PathVariable Integer parc) {
        return new ResponseEntity<>(vendasService.save(venda, parc), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Vendas venda) {
        vendasService.update(venda);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        vendasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


