package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Carros;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class CarrosRepositoryTest {
    @Autowired
    private CarrosRepository carrosRepository;
    

    @Test
    void save_SalvaCarro() {
        Carros carroCriado = criaCarro();
        Carros carroSalvo = this.carrosRepository.save(carroCriado);
        Assertions.assertThat(carroSalvo).isNotNull();
        Assertions.assertThat(carroSalvo.getMarca()).isEqualTo(carroCriado.getMarca());
        Assertions.assertThat(carroSalvo.getId()).isNotNull();

    }

    @Test
    void update_AtualizaCarro() {
        Carros carroCriado = criaCarro();
        Carros carroSalvo = this.carrosRepository.save(carroCriado);
        carroSalvo.setMarca("Ford");
        Carros carroAtualizado = this.carrosRepository.save(carroSalvo);
        Assertions.assertThat(carroAtualizado).isNotNull();
        Assertions.assertThat(carroAtualizado.getMarca()).isEqualTo(carroSalvo.getMarca());
        Assertions.assertThat(carroAtualizado.getId()).isNotNull();
    }

    @Test
    void delete_DeletaCarro() {
        Carros carroCriado = criaCarro();
        Carros carroSalvo = this.carrosRepository.save(carroCriado);
        carrosRepository.delete(carroSalvo);
        Optional<Carros> carroDeletado = carrosRepository.findById(carroSalvo.getId());
        Assertions.assertThat(carroDeletado).isEmpty();
    }

    @Test
    void findById_BuscaCarroPeloId() {
        Carros carroCriado = criaCarro();
        Carros carroSalvo = this.carrosRepository.save(carroCriado);
        Long id = carroSalvo.getId();
        Optional<Carros> carroId = this.carrosRepository.findById(id);
        Assertions.assertThat(carroId).isNotNull().isPresent();
    }

    @Test
    void findALL_BuscaTodosCarros() {
        Carros carroCriado = criaCarro();
        Carros carroSalvo = this.carrosRepository.save(carroCriado);
        List<Carros> listaCarros = this.carrosRepository.findAll();
        Assertions.assertThat(listaCarros).isNotNull().isNotEmpty();

    }

    private Carros criaCarro() {
        return Carros.builder()
                .modelo("astra")
                .marca("chevrolet")
                .ano(2012)
                .preco(BigDecimal.valueOf(16000))
                .build();
    }
}