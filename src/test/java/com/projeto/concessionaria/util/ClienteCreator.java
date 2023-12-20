package com.projeto.concessionaria.util;

import com.projeto.concessionaria.entity.Clientes;

import java.math.BigDecimal;

public class ClienteCreator {
    public static Clientes criaClienteParaSerSalvo() {
        return Clientes.builder()
                .nome("Bianca")
                .saldo(BigDecimal.valueOf(40000))
                .CPF("104.030.350-15")
                .build();
    }

    public static Clientes criaClienteValido() {
        return Clientes.builder()
                .id(1L)
                .nome("Bianca")
                .saldo(BigDecimal.valueOf(40000))
                .CPF("104.030.350-15")
                .build();
    }

    public static Clientes criaClienteParaSerAtualizado() {
        return Clientes.builder()
                .id(1L)
                .nome("Patricia")
                .saldo(BigDecimal.valueOf(80000))
                .CPF("104.030.350-15")
                .build();
    }
}

