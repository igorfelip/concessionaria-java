package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.repository.CarrosRepository;
import com.projeto.concessionaria.requests.CarroPostRequestBodyCreator;
import com.projeto.concessionaria.requests.CarroPutRequestBodyCreator;
import com.projeto.concessionaria.util.CarroCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CarroServiceTest {
    @InjectMocks
    private CarrosService carrosServiceMock;
    @Mock
    private CarrosRepository carrosRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(carrosRepository.findAll())
                .thenReturn(List.of(CarroCreator.criaCarroValido()));

        BDDMockito.when(carrosRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CarroCreator.criaCarroValido()));

        BDDMockito.when(carrosRepository.save(ArgumentMatchers.any(Carros.class)))
                .thenReturn(CarroCreator.criaCarroValido());

        BDDMockito.doNothing().when(carrosRepository).delete(ArgumentMatchers.any(Carros.class));
    }

    @Test
    void findAll_RetornaTodosCarros() {
        String marcaEsperada = CarroCreator.criaCarroValido().getMarca();
        List<Carros> carros = carrosServiceMock.findAll();
        Assertions.assertThat(carros).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(carros.get(0).getMarca()).isEqualTo(marcaEsperada);
    }

    @Test
    void findById_RetornaCarroPeloId() {
        Long id = CarroCreator.criaCarroValido().getId();
        Carros body = carrosServiceMock.findById(1L);
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaCarro() {
        Carros body = carrosServiceMock.save(CarroPostRequestBodyCreator.criaCarroPostRequestBody());

        Assertions.assertThat(body).isNotNull().isEqualTo(CarroCreator.criaCarroValido());
    }

    @Test
    void update_AtualizaCarro() {
        Assertions.assertThatCode(() -> carrosServiceMock
                        .update(CarroPutRequestBodyCreator.criaCarroPutRequestBody()))
                .doesNotThrowAnyException();


    }

    @Test
    void delete_DeletaCarro() {
        Assertions.assertThatCode(() -> carrosServiceMock.delete(1L)).doesNotThrowAnyException();

    }

    @Test
    void findALL_RetornaListaVazia() {
        BDDMockito.when(carrosServiceMock.findAll()).thenReturn(Collections.emptyList());
        List<Carros> listaCarros = this.carrosServiceMock.findAll();
        Assertions.assertThat(listaCarros).isEmpty();

    }

}


