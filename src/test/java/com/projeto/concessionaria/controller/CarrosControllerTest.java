package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.requests.CarroPostRequestBodyCreator;
import com.projeto.concessionaria.requests.CarroPutRequestBodyCreator;
import com.projeto.concessionaria.requests.CarrosPostRequestBody;
import com.projeto.concessionaria.requests.CarrosPutRequestBody;
import com.projeto.concessionaria.service.CarrosService;
import com.projeto.concessionaria.util.CarroCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class CarrosControllerTest {
    @InjectMocks
    private CarrosController carrosController;
    @Mock
    private CarrosService carrosService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(carrosService.findAll())
                .thenReturn(List.of(CarroCreator.criaCarroValido()));

        BDDMockito.when(carrosService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(CarroCreator.criaCarroValido());

        BDDMockito.when(carrosService.save(ArgumentMatchers.any(CarrosPostRequestBody.class)))
                .thenReturn(CarroCreator.criaCarroValido());

        BDDMockito.doNothing().when(carrosService).update(ArgumentMatchers.any(CarrosPutRequestBody.class));

        BDDMockito.doNothing().when(carrosService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void findAll_RetornaTodosCarros() {
        String marcaEsperada = CarroCreator.criaCarroValido().getMarca();
        List<Carros> carros = carrosController.findAll().getBody();
        Assertions.assertThat(carros).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(carros.get(0).getMarca()).isEqualTo(marcaEsperada);
    }

    @Test
    void findById_RetornaCarroPeloId() {
        Long id = CarroCreator.criaCarroValido().getId();
        Carros body = carrosController.findById(1L).getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaCarro() {
        Carros body = carrosController.save(CarroPostRequestBodyCreator.criaCarroPostRequestBody()).getBody();

        Assertions.assertThat(body).isNotNull().isEqualTo(CarroCreator.criaCarroValido());
    }

    @Test
    void update_AtualizaCarro() {
        Assertions.assertThatCode(() -> carrosController
                        .update(CarroPutRequestBodyCreator.criaCarroPutRequestBody()).getBody())
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = carrosController.update(CarroPutRequestBodyCreator.criaCarroPutRequestBody());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void delete_DeletaCarro() {
        Assertions.assertThatCode(() -> carrosController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = carrosController.delete(1L);

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}
