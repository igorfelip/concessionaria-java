package com.projeto.concessionaria.integration;

import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.repository.CarrosRepository;
import com.projeto.concessionaria.repository.ClientesRepository;
import com.projeto.concessionaria.repository.VendasRepository;
import com.projeto.concessionaria.repository.VendedoresRepository;
import com.projeto.concessionaria.requests.VendaPostRequestBodyCreator;
import com.projeto.concessionaria.requests.VendasPostRequestBody;
import com.projeto.concessionaria.util.VendaCreator;
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
class VendaControllerIT {
    @Autowired
    VendasRepository vendasRepository;
    @Autowired
    CarrosRepository carrosRepository;
    @Autowired
    VendedoresRepository vendedoresRepository;
    @Autowired
    ClientesRepository clientesRepository;
    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplate;

    @Test
    void findAll_RetornaTodosVendas() {
        carrosRepository.save(VendaCreator.criaVendaParaSerSalva().getCarro());
        vendedoresRepository.save(VendaCreator.criaVendaParaSerSalva().getVendedor());
        clientesRepository.save(VendaCreator.criaVendaParaSerSalva().getCliente());
        Vendas save = vendasRepository.save(VendaCreator.criaVendaParaSerSalva());
        String marca = save.getCarro().getMarca();
        List<Vendas> body = testRestTemplate.exchange("/vendas", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Vendas>>() {
                }).getBody();

        Assertions.assertThat(body).isNotEmpty().isNotNull().hasSize(1);
        Assertions.assertThat(body.get(0).getCarro().getMarca()).isEqualTo(marca);
    }

    @Test
    void findById_RetornaVendaPeloId() {
        carrosRepository.save(VendaCreator.criaVendaParaSerSalva().getCarro());
        vendedoresRepository.save(VendaCreator.criaVendaParaSerSalva().getVendedor());
        clientesRepository.save(VendaCreator.criaVendaParaSerSalva().getCliente());
        Vendas save = vendasRepository.save(VendaCreator.criaVendaParaSerSalva());

        Long id = save.getId();

        Vendas body = testRestTemplate.getForObject("/vendas/{id}", Vendas.class, id,
                new ParameterizedTypeReference<Vendas>() {
                });
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getId()).isNotNull().isEqualTo(id);
    }

    @Test
    void save_SalvaVenda() {
        carrosRepository.save(VendaCreator.criaVendaParaSerSalva().getCarro());
        vendedoresRepository.save(VendaCreator.criaVendaParaSerSalva().getVendedor());
        clientesRepository.save(VendaCreator.criaVendaParaSerSalva().getCliente());
        VendasPostRequestBody vendasPostRequestBody = VendaPostRequestBodyCreator.criaVendaPostRequestBody();
        ResponseEntity<Vendas> body = testRestTemplate.postForEntity("/vendas//admin/{par}", vendasPostRequestBody
                , Vendas.class, 2);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void update_AtualizaVenda() {
        carrosRepository.save(VendaCreator.criaVendaParaSerSalva().getCarro());
        vendedoresRepository.save(VendaCreator.criaVendaParaSerSalva().getVendedor());
        clientesRepository.save(VendaCreator.criaVendaParaSerSalva().getCliente());
        Vendas save = vendasRepository.save(VendaCreator.criaVendaParaSerSalva());

        save.getCarro().setMarca("Peugeot");

        ResponseEntity<Void> body = testRestTemplate.exchange("/vendas/admin", HttpMethod.PUT,
                new HttpEntity<>(save), Void.class);

        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void delete_DeletaVenda() {
        carrosRepository.save(VendaCreator.criaVendaParaSerSalva().getCarro());
        vendedoresRepository.save(VendaCreator.criaVendaParaSerSalva().getVendedor());
        clientesRepository.save(VendaCreator.criaVendaParaSerSalva().getCliente());
        Vendas save = vendasRepository.save(VendaCreator.criaVendaParaSerSalva());

        ResponseEntity<Void> body = testRestTemplate.exchange("/vendas//admin/{id}", HttpMethod.DELETE,
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




