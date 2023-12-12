package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.repository.ClientesRepository;
import com.projeto.concessionaria.requests.ClientePostRequestBodyCreator;
import com.projeto.concessionaria.requests.ClientePutRequestBodyCreator;
import com.projeto.concessionaria.util.CarroCreator;
import com.projeto.concessionaria.util.ClienteCreator;
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
class ClienteServiceTest {
    @InjectMocks
    private ClientesService clientesServiceMockMock;
    @Mock
    private ClientesRepository clientesRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(clientesRepository.findAll())
                .thenReturn(List.of(ClienteCreator.criaClienteValido()));

        BDDMockito.when(clientesRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClienteCreator.criaClienteValido()));

        BDDMockito.when(clientesRepository.save(ArgumentMatchers.any(Clientes.class)))
                .thenReturn(ClienteCreator.criaClienteValido());

        BDDMockito.doNothing().when(clientesRepository).delete(ArgumentMatchers.any(Clientes.class));
    }

    @Test
    void findAll_RetornaTodosClientes() {
        String nomeEsperado = ClienteCreator.criaClienteValido().getNome();
        List<Clientes> clientes = clientesServiceMockMock.findAll();
        Assertions.assertThat(clientes).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(clientes.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    void findById_RetornaClientePeloId() {
        Long id = CarroCreator.criaCarroValido().getId();
        Clientes body = clientesServiceMockMock.findById(1L);
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaCliente() {
        Clientes body = clientesServiceMockMock.save(ClientePostRequestBodyCreator.criaClientePostRequestBody());

        Assertions.assertThat(body).isNotNull().isEqualTo(ClienteCreator.criaClienteValido());
    }

    @Test
    void update_AtualizaCliente() {
        Assertions.assertThatCode(() -> clientesServiceMockMock
                        .update(ClientePutRequestBodyCreator.criaClientePutRequestBody()))
                .doesNotThrowAnyException();


    }

    @Test
    void delete_DeletaCliente() {
        Assertions.assertThatCode(() -> clientesServiceMockMock.delete(1L)).doesNotThrowAnyException();

    }

    @Test
    void findALL_RetornaListaVazia() {
        BDDMockito.when(clientesServiceMockMock.findAll()).thenReturn(Collections.emptyList());
        List<Clientes> listaClientes = this.clientesServiceMockMock.findAll();
        Assertions.assertThat(listaClientes).isEmpty();

    }

}


