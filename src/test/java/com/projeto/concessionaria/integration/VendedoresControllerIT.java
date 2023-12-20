package com.projeto.concessionaria.integration;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.repository.VendedoresRepository;
import com.projeto.concessionaria.requests.VendedorPostRequestBodyCreator;
import com.projeto.concessionaria.requests.VendedoresPostRequestBody;
import com.projeto.concessionaria.util.VendedorCreator;
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
class VendedoresControllerIT {
    @Autowired
    VendedoresRepository vendedoresRepository;
    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplate;

    @Test
    void findAll_RetornaTodosVendedores() {
        Vendedores save = vendedoresRepository.save(VendedorCreator.criaVendedorParaSerSalvo());
        String nome = save.getNome();
        List<Vendedores> body = testRestTemplate.exchange("/vendedores", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Vendedores>>() {
                }).getBody();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getNome()).isEqualTo(nome);
    }

    @Test
    void findById_RetornaVendedorPeloId() {
        Vendedores save = vendedoresRepository.save(VendedorCreator.criaVendedorParaSerSalvo());

        Long id = save.getId();

        Vendedores body = testRestTemplate.getForObject("/vendedores/{id}", Vendedores.class, id,
                new ParameterizedTypeReference<Vendedores>() {
                });
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaVendedor() {
        VendedoresPostRequestBody vendedoresPostRequestBody = VendedorPostRequestBodyCreator.criaVendedorPostRequestBody();
        ResponseEntity<Vendedores> body = testRestTemplate.postForEntity("/vendedores/admin", vendedoresPostRequestBody
                , Vendedores.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void update_AtualizaVendedor() {
        Vendedores save = vendedoresRepository.save(VendedorCreator.criaVendedorParaSerSalvo());

        save.setNome("Ailton");

        ResponseEntity<Void> body = testRestTemplate.exchange("/vendedores/admin", HttpMethod.PUT,
                new HttpEntity<>(save), Void.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void delete_DeletaVendedor() {
        Vendedores save = vendedoresRepository.save(VendedorCreator.criaVendedorParaSerSalvo());

        ResponseEntity<Void> body = testRestTemplate.exchange("/vendedores/admin/{id}", HttpMethod.DELETE,
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
    }
}



