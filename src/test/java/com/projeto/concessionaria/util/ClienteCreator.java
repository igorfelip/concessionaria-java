package com.projeto.concessionaria.util;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.entity.Clientes;

import java.math.BigDecimal;
import java.util.List;

public class ClienteCreator {
    public static Clientes criaClienteParaSerSalvo() {
        return Clientes.builder()
                .nome("Bianca")
                .saldo(BigDecimal.valueOf(40000))
                .documento(12414135L)
                .build();
    }

    public static Clientes criaClienteValido() {
        return Clientes.builder()
                .id(1L)
                .nome("Bianca")
                .saldo(BigDecimal.valueOf(40000))
                .documento(12414135L)
                .build();
    }

    public static Clientes criaClienteParaSerAtualizado() {
        return Clientes.builder()
                .id(1L)
                .nome("Patricia")
                .saldo(BigDecimal.valueOf(80000))
                .documento(5737542765L)
                .build();
    }
}

