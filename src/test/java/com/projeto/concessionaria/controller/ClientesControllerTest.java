package com.projeto.concessionaria.controller;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.requests.ClientePostRequestBodyCreator;
import com.projeto.concessionaria.requests.ClientePutRequestBodyCreator;
import com.projeto.concessionaria.requests.ClientesPostRequestBody;
import com.projeto.concessionaria.requests.ClientesPutRequestBody;
import com.projeto.concessionaria.service.ClientesService;
import com.projeto.concessionaria.util.ClienteCreator;
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
class ClientesControllerTest {

    @InjectMocks
    private ClientesController clientesController;
    @Mock
    private ClientesService clienteService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(clienteService.findAll())
                .thenReturn(List.of(ClienteCreator.criaClienteValido()));

        BDDMockito.when(clienteService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.criaClienteValido());

        BDDMockito.when(clienteService.save(ArgumentMatchers.any(ClientesPostRequestBody.class)))
                .thenReturn(ClienteCreator.criaClienteValido());

        BDDMockito.doNothing().when(clienteService).update(ArgumentMatchers.any(ClientesPutRequestBody.class));

        BDDMockito.doNothing().when(clienteService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void findAll_RetornaTodosClientes() {
        String marcaEsperada = ClienteCreator.criaClienteValido().getNome();
        List<Clientes> cliente = clientesController.findAll().getBody();
        Assertions.assertThat(cliente).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(cliente.get(0).getNome()).isEqualTo(marcaEsperada);
    }

    @Test
    void findById_RetornaClientePeloId() {
        Long id = ClienteCreator.criaClienteValido().getId();
        Clientes body = clientesController.findById(1L).getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaCliente() {
        Clientes body = clientesController.save(ClientePostRequestBodyCreator.criaClientePostRequestBody()).getBody();

        Assertions.assertThat(body).isNotNull().isEqualTo(ClienteCreator.criaClienteValido());
    }

    @Test
    void update_AtualizaCliente() {
        Assertions.assertThatCode(() -> clientesController
                        .update(ClientePutRequestBodyCreator.criaClientePutRequestBody()).getBody())
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = clientesController.update(ClientePutRequestBodyCreator.criaClientePutRequestBody());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void delete_DeletaCliente() {
        Assertions.assertThatCode(() -> clientesController.delete(1l)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = clientesController.delete(1L);

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}

