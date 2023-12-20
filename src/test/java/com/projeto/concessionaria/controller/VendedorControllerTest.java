package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.requests.VendedorPostRequestBodyCreator;
import com.projeto.concessionaria.requests.VendedorPutRequestBodyCreator;
import com.projeto.concessionaria.requests.VendedoresPostRequestBody;
import com.projeto.concessionaria.requests.VendedoresPutRequestBody;
import com.projeto.concessionaria.service.VendedoresService;
import com.projeto.concessionaria.util.VendedorCreator;
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
class VendedorControllerTest {

    @InjectMocks
    private VendedoresController vendedorController;
    @Mock
    private VendedoresService vendedorService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(vendedorService.findAll())
                .thenReturn(List.of(VendedorCreator.criaVendedorValido()));

        BDDMockito.when(vendedorService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(VendedorCreator.criaVendedorValido());

        BDDMockito.when(vendedorService.save(ArgumentMatchers.any(VendedoresPostRequestBody.class)))
                .thenReturn(VendedorCreator.criaVendedorValido());

        BDDMockito.doNothing().when(vendedorService).update(ArgumentMatchers.any(VendedoresPutRequestBody.class));

        BDDMockito.doNothing().when(vendedorService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void findAll_RetornaTodosVendedores() {
        String marcaEsperada = VendedorCreator.criaVendedorValido().getNome();
        List<Vendedores> vendedor = vendedorController.findAll().getBody();
        Assertions.assertThat(vendedor).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(vendedor.get(0).getNome()).isEqualTo(marcaEsperada);
    }

    @Test
    void findById_RetornaVendedorPeloId() {
        Long id = VendedorCreator.criaVendedorValido().getId();
        Vendedores body = vendedorController.findById(1L).getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaVendedor() {
        Vendedores body = vendedorController.save(VendedorPostRequestBodyCreator.criaVendedorPostRequestBody()).getBody();

        Assertions.assertThat(body).isNotNull().isEqualTo(VendedorCreator.criaVendedorValido());
    }

    @Test
    void update_AtualizaVendedor() {
        Assertions.assertThatCode(() -> vendedorController
                        .update(VendedorPutRequestBodyCreator.criaVendedorPutRequestBody()).getBody())
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = vendedorController.update(VendedorPutRequestBodyCreator.criaVendedorPutRequestBody());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void delete_DeletaVendedor() {
        Assertions.assertThatCode(() -> vendedorController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = vendedorController.delete(1L);

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}

