package com.projeto.concessionaria.requests;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class VendedoresPutRequestBody {
    @NotBlank(message = "Insira um Id")
    private Long id;
    @NotBlank(message = "Insira um nome")
    private String nome;
    @DecimalMin(value = "0", message = "Insira um valor")
    private BigDecimal salario;

}
