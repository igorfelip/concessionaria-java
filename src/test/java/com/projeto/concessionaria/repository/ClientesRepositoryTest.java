package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class ClientesRepositoryTest {
    @Autowired
    private ClientesRepository clientesRepository;


    @Test
    void save_SalvaCliente() {
        Clientes clienteCriado = ClienteCreator.criaClienteValido();
        Clientes clienteSalvo = this.clientesRepository.save(clienteCriado);
        Assertions.assertThat(clienteSalvo).isNotNull();
        Assertions.assertThat(clienteSalvo.getNome()).isEqualTo(clienteCriado.getNome());
        Assertions.assertThat(clienteSalvo.getId()).isNotNull();

    }

    @Test
    void update_AtualizaCliente() {
        Clientes clienteCriado = ClienteCreator.criaClienteValido();
        Clientes clienteSalvo = this.clientesRepository.save(clienteCriado);
        clienteSalvo.setNome("Carlos");
        Clientes clienteAtualizado = this.clientesRepository.save(clienteSalvo);
        Assertions.assertThat(clienteAtualizado).isNotNull();
        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clienteSalvo.getNome());
        Assertions.assertThat(clienteAtualizado.getId()).isNotNull();
    }

    @Test
    void delete_DeletaCliente() {
        Clientes clienteCriado = ClienteCreator.criaClienteValido();
        Clientes clienteSalvo = this.clientesRepository.save(clienteCriado);
        clientesRepository.delete(clienteSalvo);
        Optional<Clientes> clienteDeletado = clientesRepository.findById(clienteSalvo.getId());
        Assertions.assertThat(clienteDeletado).isEmpty();
    }

    @Test
    void findById_BuscaClientePeloId() {
        Clientes clienteCriado = ClienteCreator.criaClienteValido();
        Clientes clienteSalvo = this.clientesRepository.save(clienteCriado);
        Long id = clienteSalvo.getId();
        Optional<Clientes> clienteId = this.clientesRepository.findById(id);
        Assertions.assertThat(clienteId).isNotNull().isPresent();
    }

    @Test
    void findALL_BuscaTodosCliente() {
        Clientes clienteCriado = ClienteCreator.criaClienteValido();
        this.clientesRepository.save(clienteCriado);
        List<Clientes> listaCliente = this.clientesRepository.findAll();
        Assertions.assertThat(listaCliente).isNotNull().isNotEmpty();

    }

    @Test
    void findALL_RetornaListaVazia() {
        List<Clientes> listaCliente = this.clientesRepository.findAll();
        Assertions.assertThat(listaCliente).isEmpty();

    }


}