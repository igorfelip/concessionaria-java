package com.projeto.concessionaria.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ClientesPostRequestBody {
    @NotBlank(message = "Insira um nome")
    private String nome;
    @Range(min = 0, max = 99999999999L, message = "Insira um documento válido")
    private Long documento;
    private BigDecimal saldo;
}
