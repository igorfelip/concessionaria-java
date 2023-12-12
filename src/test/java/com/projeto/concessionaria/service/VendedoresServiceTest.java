package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.repository.VendedoresRepository;
import com.projeto.concessionaria.requests.VendedorPostRequestBodyCreator;
import com.projeto.concessionaria.requests.VendedorPutRequestBodyCreator;
import com.projeto.concessionaria.util.CarroCreator;
import com.projeto.concessionaria.util.VendedorCreator;
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
class VendedoresServiceTest {
    @InjectMocks
    private VendedoresService vendedoresServiceMockMock;
    @Mock
    private VendedoresRepository vendedoresRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(vendedoresRepository.findAll())
                .thenReturn(List.of(VendedorCreator.criaVendedorValido()));

        BDDMockito.when(vendedoresRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(VendedorCreator.criaVendedorValido()));

        BDDMockito.when(vendedoresRepository.save(ArgumentMatchers.any(Vendedores.class)))
                .thenReturn(VendedorCreator.criaVendedorValido());

        BDDMockito.doNothing().when(vendedoresRepository).delete(ArgumentMatchers.any(Vendedores.class));
    }

    @Test
    void findAll_RetornaTodosVendedores() {
        String nomeEsperado = VendedorCreator.criaVendedorValido().getNome();
        List<Vendedores> vendedores = vendedoresServiceMockMock.findAll();
        Assertions.assertThat(vendedores).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(vendedores.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    void findById_RetornaVendedorPeloId() {
        Long id = CarroCreator.criaCarroValido().getId();
        Vendedores body = vendedoresServiceMockMock.findById(1L);
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaVendedor() {
        Vendedores body = vendedoresServiceMockMock.save(VendedorPostRequestBodyCreator.criaVendedorPostRequestBody());

        Assertions.assertThat(body).isNotNull().isEqualTo(VendedorCreator.criaVendedorValido());
    }

    @Test
    void update_AtualizaVendedor() {
        Assertions.assertThatCode(() -> vendedoresServiceMockMock
                        .update(VendedorPutRequestBodyCreator.criaVendedorPutRequestBody()))
                .doesNotThrowAnyException();


    }

    @Test
    void delete_DeletaVendedor() {
        Assertions.assertThatCode(() -> vendedoresServiceMockMock.delete(1L)).doesNotThrowAnyException();

    }

    @Test
    void findALL_RetornaListaVazia() {
        BDDMockito.when(vendedoresServiceMockMock.findAll()).thenReturn(Collections.emptyList());
        List<Vendedores> listaVendedores = this.vendedoresServiceMockMock.findAll();
        Assertions.assertThat(listaVendedores).isEmpty();

    }

}


