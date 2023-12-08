package com.projeto.concessionaria.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ClientesPutRequestBody {
    @NotBlank(message = "Insira um Id")
    private Long id;
    @NotBlank(message = "Insira um nome")
    private String nome;
    @Range(max = 11, min = 9, message = "Insira um documento válido")
    private Long documento;
    private BigDecimal saldo;
}
