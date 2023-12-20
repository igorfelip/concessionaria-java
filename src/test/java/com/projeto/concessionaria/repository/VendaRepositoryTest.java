package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.entity.Vendedores;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class VendaRepositoryTest {
    @Autowired
    private VendasRepository vendasRepository;
    @Autowired
    private CarrosRepository carrosRepository;
    @Autowired
    private VendedoresRepository vendedoresRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    @Test
    void save_SalvaVenda() {
        Vendas vendaCriado = criaVenda();
        Vendas vendaSalvo = this.vendasRepository.save(vendaCriado);
        Assertions.assertThat(vendaSalvo).isNotNull();
        Assertions.assertThat(vendaSalvo.getVendedor().getNome()).isEqualTo(vendaCriado.getVendedor().getNome());
        Assertions.assertThat(vendaSalvo.getCliente().getNome()).isEqualTo(vendaCriado.getCliente().getNome());
        Assertions.assertThat(vendaSalvo.getId()).isNotNull();

    }

    @Test
    void update_AtualizaVenda() {
        Vendas vendaCriado = criaVenda();
        Vendas vendaSalvo = this.vendasRepository.save(vendaCriado);
        vendaSalvo.getVendedor().setNome("Adailton");
        Vendas vendaAtualizado = this.vendasRepository.save(vendaSalvo);
        Assertions.assertThat(vendaAtualizado).isNotNull();
        Assertions.assertThat(vendaAtualizado.getVendedor().getNome()).isEqualTo(vendaSalvo.getVendedor().getNome());
        Assertions.assertThat(vendaAtualizado.getId()).isNotNull();
    }

    @Test
    void delete_DeletaVenda() {
        Vendas vendaCriado = criaVenda();
        Vendas vendaSalvo = this.vendasRepository.save(vendaCriado);
        vendasRepository.delete(vendaSalvo);
        Optional<Vendas> vendaDeletado = vendasRepository.findById(vendaSalvo.getId());
        Assertions.assertThat(vendaDeletado).isEmpty();
    }

    @Test
    void findById_BuscaVendaPeloId() {
        Vendas vendaCriado = criaVenda();
        Vendas vendaSalvo = this.vendasRepository.save(vendaCriado);
        Long id = vendaSalvo.getId();
        Optional<Vendas> vendaId = this.vendasRepository.findById(id);
        Assertions.assertThat(vendaId).isNotNull().isPresent();
    }

    @Test
    void findALL_BuscaTodasVendas() {
        Vendas vendaCriado = criaVenda();
        this.vendasRepository.save(vendaCriado);
        List<Vendas> listaVendas = vendasRepository.findAll();
        Assertions.assertThat(listaVendas).isNotNull().isNotEmpty();

    }

    @Test
    void findALL_RetornaListaVazia() {
        List<Vendas> listaVendas = this.vendasRepository.findAll();
        Assertions.assertThat(listaVendas).isEmpty();

    }


    private Vendas criaVenda() {
        Carros save = carrosRepository.save(Carros.builder().build());
        Vendedores save1 = vendedoresRepository.save(Vendedores.builder().build());
        Clientes save2 = clientesRepository.save(Clientes.builder().build());
        return Vendas.builder()
                .vendedor(save1)
                .carro(save)
                .cliente(save2)
                .build();

    }
}

