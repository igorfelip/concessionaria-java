package com.projeto.concessionaria.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendedoresPutRequestBody {
    @NotNull(message = "Insira um Id")
    private Long id;
    @NotBlank(message = "Insira um nome")
    private String nome;
    @DecimalMin(value = "0", message = "Insira um valor")
    private BigDecimal salario;

}
