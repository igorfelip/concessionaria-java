package com.projeto.concessionaria.integration;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.repository.CarrosRepository;
import com.projeto.concessionaria.requests.CarroPostRequestBodyCreator;
import com.projeto.concessionaria.requests.CarrosPostRequestBody;
import com.projeto.concessionaria.util.CarroCreator;
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
class CarrosControllerIT {
    @Autowired
    CarrosRepository carrosRepository;
    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplate;

    @Test
    void findAll_RetornaTodosCarros() {
        Carros save = carrosRepository.save(CarroCreator.criaCarroParaSerSalvo());
        String marca = save.getMarca();
        List<Carros> body = testRestTemplate.exchange("/carros", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Carros>>() {
                }).getBody();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getMarca()).isEqualTo(marca);
    }

    @Test
    void findById_RetornaCarroPeloId() {
        Carros save = carrosRepository.save(CarroCreator.criaCarroParaSerSalvo());

        Long id = save.getId();

        Carros body = testRestTemplate.getForObject("/carros/{id}", Carros.class, id,
                new ParameterizedTypeReference<Carros>() {
                });
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaCarro() {
        CarrosPostRequestBody carrosPostRequestBody = CarroPostRequestBodyCreator.criaCarroPostRequestBody();
        ResponseEntity<Carros> body = testRestTemplate.postForEntity("/carros/admin", carrosPostRequestBody
                , Carros.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void update_AtualizaCarro() {
        Carros save = carrosRepository.save(CarroCreator.criaCarroParaSerSalvo());

        save.setMarca("Peugeot");

        ResponseEntity<Void> body = testRestTemplate.exchange("/carros/admin", HttpMethod.PUT,
                new HttpEntity<>(save), Void.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void delete_DeletaCarro() {
        Carros save = carrosRepository.save(CarroCreator.criaCarroParaSerSalvo());

        ResponseEntity<Void> body = testRestTemplate.exchange("/carros/admin/{id}", HttpMethod.DELETE,
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



