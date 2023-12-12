package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.entity.Vendedores;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendasPutRequestBody {
    @NotNull(message = "Insira um id valido")
    private Long id;
    @NotNull(message = "Insira um carro")
    private Carros carro;
    @NotNull(message = "Insira um cliente")
    private Clientes cliente;
    @NotNull(message = "Insira um vendedor")
    private Vendedores vendedor;
}
