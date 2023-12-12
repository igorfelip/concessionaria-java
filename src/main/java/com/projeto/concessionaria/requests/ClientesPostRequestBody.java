package com.projeto.concessionaria.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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
    @Range(min = 0, max = 99999999999L, message = "Insira um documento válido")
    private Long documento;
    @DecimalMin(value = "0", message = "Insira um valor")
    private BigDecimal saldo;
}
