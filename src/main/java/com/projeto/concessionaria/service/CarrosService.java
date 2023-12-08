package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.exception.BadRequestException;
import com.projeto.concessionaria.mapper.CarroMapper;
import com.projeto.concessionaria.repository.CarrosRepository;
import com.projeto.concessionaria.requests.CarrosPostRequestBody;
import com.projeto.concessionaria.requests.CarrosPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarrosService {
    private final CarrosRepository carrosRepository;

    public List<Carros> findAll() {
        return carrosRepository.findAll();
    }

    public Carros findById(Long id) {
        return carrosRepository.findById(id).orElseThrow(() ->
                (new BadRequestException("Carro não encontrado")));
    }

    public Carros save(CarrosPostRequestBody carrosPostRequestBody) {
        return carrosRepository.save(CarroMapper.INSTANCE.toCarro(carrosPostRequestBody));
    }

    public void update(CarrosPutRequestBody carrosPutRequestBody) {
        Carros byId = findById(carrosPutRequestBody.getId());
        Carros carro = CarroMapper.INSTANCE.toCarro(carrosPutRequestBody);
        carro.setId(byId.getId());
        carrosRepository.save(carro);
    }

    public void delete(Long id) {
        Carros byId = findById(id);
        carrosRepository.delete(byId);

    }
}


