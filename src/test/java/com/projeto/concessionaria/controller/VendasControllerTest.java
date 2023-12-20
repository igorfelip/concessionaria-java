package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.requests.VendaPostRequestBodyCreator;
import com.projeto.concessionaria.requests.VendaPutRequestBodyCreator;
import com.projeto.concessionaria.requests.VendasPutRequestBody;
import com.projeto.concessionaria.service.VendasService;
import com.projeto.concessionaria.util.VendaCreator;
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
class VendasControllerTest {
    @InjectMocks
    private VendasController vendasController;
    @Mock
    private VendasService vendasService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(vendasService.findAll())
                .thenReturn(List.of(VendaCreator.criaVendaValida()));

        BDDMockito.when(vendasService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(VendaCreator.criaVendaValida());

        BDDMockito.when(vendasService.save(ArgumentMatchers.any(), ArgumentMatchers.anyInt()))
                .thenReturn(VendaCreator.criaVendaValida());

        BDDMockito.doNothing().when(vendasService).update(ArgumentMatchers.any(VendasPutRequestBody.class));

        BDDMockito.doNothing().when(vendasService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void findAll_RetornaTodosVendas() {
        String marcaEsperada = VendaCreator.criaVendaValida().getCarro().getMarca();
        List<Vendas> vendas = vendasController.findAll().getBody();
        Assertions.assertThat(vendas).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(vendas.get(0).getCarro().getMarca()).isEqualTo(marcaEsperada);
    }

    @Test
    void findById_RetornaVendaPeloId() {
        Long id = VendaCreator.criaVendaValida().getId();
        Vendas body = vendasController.findById(1L).getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaVenda() {
        Vendas body = vendasController.save(VendaPostRequestBodyCreator.criaVendaPostRequestBody(), 2).getBody();

        Assertions.assertThat(body).isNotNull().isEqualTo(VendaCreator.criaVendaValida());
    }

    @Test
    void update_AtualizaVenda() {
        Assertions.assertThatCode(() -> vendasController
                        .update(VendaPutRequestBodyCreator.criaVendaPutRequestBody()).getBody())
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = vendasController.update(VendaPutRequestBodyCreator.criaVendaPutRequestBody());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void delete_DeletaVenda() {
        Assertions.assertThatCode(() -> vendasController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = vendasController.delete(1L);

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}
