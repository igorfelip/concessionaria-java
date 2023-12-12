package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.util.CarroCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class CarroRepositoryTest {

    @Autowired
    private CarrosRepository carroRepository;


    @Test
    void save_SalvaCarro() {
        Carros carroCriado = CarroCreator.criaCarroValido();
        Carros carroSalvo = this.carroRepository.save(carroCriado);
        Assertions.assertThat(carroSalvo).isNotNull();
        Assertions.assertThat(carroSalvo.getMarca()).isEqualTo(carroCriado.getMarca());
        Assertions.assertThat(carroSalvo.getId()).isNotNull();

    }

    @Test
    void update_AtualizaCarro() {
        Carros carroCriado = CarroCreator.criaCarroValido();
        Carros carroSalvo = this.carroRepository.save(carroCriado);
        carroSalvo.setMarca("Peugeot");
        Carros carroAtualizado = this.carroRepository.save(carroSalvo);
        Assertions.assertThat(carroAtualizado).isNotNull();
        Assertions.assertThat(carroAtualizado.getMarca()).isEqualTo(carroSalvo.getMarca());
        Assertions.assertThat(carroAtualizado.getId()).isNotNull();
    }

    @Test
    void delete_DeletaCarro() {
        Carros carroCriado = CarroCreator.criaCarroValido();
        Carros carroSalvo = this.carroRepository.save(carroCriado);
        carroRepository.delete(carroSalvo);
        Optional<Carros> carroDeletado = carroRepository.findById(carroSalvo.getId());
        Assertions.assertThat(carroDeletado).isEmpty();
    }

    @Test
    void findById_BuscaCarroPeloId() {
        Carros carroCriado = CarroCreator.criaCarroValido();
        Carros carroSalvo = this.carroRepository.save(carroCriado);
        Long id = carroSalvo.getId();
        Optional<Carros> carroId = this.carroRepository.findById(id);
        Assertions.assertThat(carroId).isNotNull().isPresent();
    }

    @Test
    void findALL_BuscaTodosCarros() {
        Carros carroCriado = CarroCreator.criaCarroValido();
        this.carroRepository.save(carroCriado);
        List<Carros> listaCarros = this.carroRepository.findAll();
        Assertions.assertThat(listaCarros).isNotNull().isNotEmpty();

    }

    @Test
    void findALL_RetornaListaVazia() {
        List<Carros> listaCarros = this.carroRepository.findAll();
        Assertions.assertThat(listaCarros).isEmpty();

    }


}



