package com.projeto.concessionaria.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrosPutRequestBody {
    @NotNull(message = "Insira um Id")
    private Long id;
    @NotBlank(message = "Insira uma marca")
    private String marca;
    @NotBlank(message = "Insira um modelo")
    private String modelo;
    @Range(min = 1980, max = 2099, message = "Insira um valor")
    private Integer ano;
    @DecimalMin(value = "0", message = "Insira um valor")
    private BigDecimal preco;
}
