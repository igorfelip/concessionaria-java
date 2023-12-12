package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.util.VendedorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class VendedoresRepositoryTest {
    @Autowired
    private VendedoresRepository vendedoresRepository;


    @Test
    void save_SalvaVendedor() {
        Vendedores vendedorCriado = VendedorCreator.criaVendedorValido();
        Vendedores vendedorSalvo = this.vendedoresRepository.save(vendedorCriado);
        Assertions.assertThat(vendedorSalvo).isNotNull();
        Assertions.assertThat(vendedorSalvo.getNome()).isEqualTo(vendedorCriado.getNome());
        Assertions.assertThat(vendedorSalvo.getId()).isNotNull();

    }

    @Test
    void update_AtualizaVendedor() {
        Vendedores vendedorCriado = VendedorCreator.criaVendedorValido();
        Vendedores vendedorSalvo = this.vendedoresRepository.save(vendedorCriado);
        vendedorSalvo.setNome("Ailton");
        Vendedores vendedorAtualizado = this.vendedoresRepository.save(vendedorSalvo);
        Assertions.assertThat(vendedorAtualizado).isNotNull();
        Assertions.assertThat(vendedorAtualizado.getNome()).isEqualTo(vendedorSalvo.getNome());
        Assertions.assertThat(vendedorAtualizado.getId()).isNotNull();
    }

    @Test
    void delete_DeletaVendedor() {
        Vendedores vendedorCriado = VendedorCreator.criaVendedorValido();
        Vendedores vendedorSalvo = this.vendedoresRepository.save(vendedorCriado);
        vendedoresRepository.delete(vendedorSalvo);
        Optional<Vendedores> vendedorDeletado = vendedoresRepository.findById(vendedorSalvo.getId());
        Assertions.assertThat(vendedorDeletado).isEmpty();
    }

    @Test
    void findById_BuscaVendedorPeloId() {
        Vendedores vendedorCriado = VendedorCreator.criaVendedorValido();
        Vendedores vendedorSalvo = this.vendedoresRepository.save(vendedorCriado);
        Long id = vendedorSalvo.getId();
        Optional<Vendedores> vendedorId = this.vendedoresRepository.findById(id);
        Assertions.assertThat(vendedorId).isNotNull().isPresent();
    }

    @Test
    void findALL_BuscaTodosVendedores() {
        Vendedores vendedorCriado = VendedorCreator.criaVendedorValido();
        this.vendedoresRepository.save(vendedorCriado);
        List<Vendedores> listaVendedores = this.vendedoresRepository.findAll();
        Assertions.assertThat(listaVendedores).isNotNull().isNotEmpty();

    }

    @Test
    void findALL_RetornaListaVazia() {
        List<Vendedores> listaVendedores = this.vendedoresRepository.findAll();
        Assertions.assertThat(listaVendedores).isEmpty();

    }


}
