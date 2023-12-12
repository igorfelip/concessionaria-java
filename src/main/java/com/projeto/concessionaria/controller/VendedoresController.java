package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.requests.VendedoresPostRequestBody;
import com.projeto.concessionaria.requests.VendedoresPutRequestBody;
import com.projeto.concessionaria.service.VendedoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vendedores")
public class VendedoresController {
    private final VendedoresService vendedoresService;

    @GetMapping
    public ResponseEntity<List<Vendedores>> findAll() {
        return new ResponseEntity<>(vendedoresService.findAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Vendedores> findById(@PathVariable Long id) {
        return new ResponseEntity<>(vendedoresService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/admin")
    public ResponseEntity<Vendedores> save(@RequestBody @Valid VendedoresPostRequestBody vendedoresPostRequestBody) {
        return new ResponseEntity<>(vendedoresService.save(vendedoresPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(path = "/admin")
    public ResponseEntity<Void> update(@RequestBody @Valid VendedoresPutRequestBody vendedoresPutRequestBody) {
        vendedoresService.update(vendedoresPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendedoresService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


