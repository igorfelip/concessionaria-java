package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.requests.CarrosPostRequestBody;
import com.projeto.concessionaria.requests.CarrosPutRequestBody;
import com.projeto.concessionaria.service.CarrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("carros")
public class CarrosController {
    private final CarrosService carrosService;

    @GetMapping
    public ResponseEntity<List<Carros>> findAll() {
        return new ResponseEntity<>(carrosService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Carros> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carrosService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/admin")
    public ResponseEntity<Carros> save(@RequestBody @Valid CarrosPostRequestBody carro) {
        return new ResponseEntity<>(carrosService.save(carro), HttpStatus.CREATED);
    }

    @PutMapping(path = "/admin")
    public ResponseEntity<Void> update(@RequestBody @Valid CarrosPutRequestBody carro) {
        carrosService.update(carro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carrosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
