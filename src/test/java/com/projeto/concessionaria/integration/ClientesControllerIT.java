package com.projeto.concessionaria.integration;

import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.repository.ClientesRepository;
import com.projeto.concessionaria.requests.ClientePostRequestBodyCreator;
import com.projeto.concessionaria.requests.ClientesPostRequestBody;
import com.projeto.concessionaria.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 class ClientesControllerIT {
    @Autowired
    ClientesRepository clientesRepository;
    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplate;

    @Test
    void findAll_RetornaTodosCliente() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteParaSerSalvo());
        String nome = save.getNome();
        List<Clientes> body = testRestTemplate.exchange("/clientes", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Clientes>>() {
                }).getBody();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getNome()).isEqualTo(nome);
    }

    @Test
    void findById_RetornaClientePeloId() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteParaSerSalvo());

        Long id = save.getId();

        Clientes body = testRestTemplate.getForObject("/clientes/{id}", Clientes.class, id,
                new ParameterizedTypeReference<Clientes>() {
                });
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaCliente() {
        ClientesPostRequestBody clientesPostRequestBody = ClientePostRequestBodyCreator.criaClientePostRequestBody();
        ResponseEntity<Clientes> body = testRestTemplate.postForEntity("/clientes/admin", clientesPostRequestBody
                , Clientes.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void update_AtualizaCliente() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteParaSerSalvo());

        save.setNome("Junior");

        ResponseEntity<Void> body = testRestTemplate.exchange("/clientes/admin", HttpMethod.PUT,
                new HttpEntity<>(save), Void.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void delete_DeletaCliente() {
        Clientes save = clientesRepository.save(ClienteCreator.criaClienteParaSerSalvo());

        ResponseEntity<Void> body = testRestTemplate.exchange("/clientes/admin/{id}", HttpMethod.DELETE,
                null, Void.class, save.getId());

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
    @TestConfiguration
    @Lazy
    static class config {
        @Bean(name = "testRestTemplateRoleAdmin")
        public TestRestTemplate testRestTemplateRoleAdminCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder igor = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port)
                    .basicAuthentication("Igor", "testando");
            return new TestRestTemplate(igor);
        }
}}




