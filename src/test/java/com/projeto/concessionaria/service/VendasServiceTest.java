package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.repository.CarrosRepository;
import com.projeto.concessionaria.repository.VendasRepository;
import com.projeto.concessionaria.requests.VendaPostRequestBodyCreator;
import com.projeto.concessionaria.requests.VendaPutRequestBodyCreator;
import com.projeto.concessionaria.util.VendaCreator;
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
class VendasServiceTest {
    @Mock
    CarrosRepository carrosRepository;
    @InjectMocks
    private VendasService vendasServiceMock;
    @Mock
    private VendasRepository vendasRepository;
    @Mock
    private CarrosService carrosService;
    @Mock
    private VendedoresService vendedoresService;
    @Mock
    private ClientesService clientesService;


    @BeforeEach
    void setUp() {
        BDDMockito.when(vendasRepository.findAll())
                .thenReturn(List.of(VendaCreator.criaVendaValida()));

        BDDMockito.when(vendasRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(VendaCreator.criaVendaValida()));

        BDDMockito.when(vendasRepository.save(ArgumentMatchers.any(Vendas.class)))
                .thenReturn(VendaCreator.criaVendaValida());

        BDDMockito.doNothing().when(vendasRepository).delete(ArgumentMatchers.any(Vendas.class));
    }

    @Test
    void findAll_RetornaTodosVendas() {
        String nomeEsperado = VendaCreator.criaVendaValida().getVendedor().getNome();
        List<Vendas> vendedas = vendasServiceMock.findAll();
        Assertions.assertThat(vendedas).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(vendedas.get(0).getVendedor().getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    void findById_RetornaVendaPeloId() {
        Long id = VendaCreator.criaVendaValida().getId();
        Vendas body = vendasServiceMock.findById(1L);
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaVenda() {

        Vendas body = vendasServiceMock.save(VendaPostRequestBodyCreator.criaVendaPostRequestBody(), 2);

        Assertions.assertThat(body).isNotNull().isEqualTo(VendaCreator.criaVendaValida());
    }

    @Test
    void update_AtualizaVenda() {
        Assertions.assertThatCode(() -> vendasServiceMock
                        .update(VendaPutRequestBodyCreator.criaVendaPutRequestBody()))
                .doesNotThrowAnyException();


    }

    @Test
    void delete_DeletaVenda() {
        Assertions.assertThatCode(() -> vendasServiceMock.delete(1L)).doesNotThrowAnyException();

    }

    @Test
    void findALL_RetornaListaVazia() {
        BDDMockito.when(vendasServiceMock.findAll()).thenReturn(Collections.emptyList());
        List<Vendas> listaVendas = this.vendasServiceMock.findAll();
        Assertions.assertThat(listaVendas).isEmpty();

    }

}


