package com.projeto.concessionaria.util;

import com.projeto.concessionaria.entity.Carros;

import java.math.BigDecimal;

public class CarroCreator {
    public static Carros criaCarroParaSerSalvo() {
        return Carros.builder()
                .marca("Ford")
                .preco(BigDecimal.valueOf(40000))
                .ano(2012)
                .modelo("Ka")
                .build();
    }

    public static Carros criaCarroValido() {
        return Carros.builder()
                .id(1L)
                .marca("Ford")
                .preco(BigDecimal.valueOf(40000))
                .ano(2012)
                .modelo("Ka")
                .build();
    }

    public static Carros criaCarroAtualizado() {
        return Carros.builder()
                .id(1L)
                .marca("Peugeot")
                .preco(BigDecimal.valueOf(40000))
                .ano(2012)
                .modelo("2008")
                .build();
    }
}
