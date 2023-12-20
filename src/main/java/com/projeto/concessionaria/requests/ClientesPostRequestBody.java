package com.projeto.concessionaria.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientesPostRequestBody {
    @NotBlank(message = "Insira um nome")
    private String nome;
    @CPF
    private String CPF;
    @DecimalMin(value = "0", message = "Insira um valor")
    private BigDecimal saldo;
}
