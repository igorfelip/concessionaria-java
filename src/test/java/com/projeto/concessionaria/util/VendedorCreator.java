package com.projeto.concessionaria.util;

import com.projeto.concessionaria.entity.Vendedores;

import java.math.BigDecimal;

public class VendedorCreator {
    public static Vendedores criaVendedorParaSerSalvo() {
        return Vendedores.builder()
                .nome("Claudio")
                .salario(BigDecimal.valueOf(1300))
                .build();
    }

    public static Vendedores criaVendedorValido() {
        return Vendedores.builder()
                .id(1L)
                .nome("Claudio")
                .salario(BigDecimal.valueOf(1300))
                .build();
    }

    public static Vendedores criaVendedorParaSerAtualizado() {
        return Vendedores.builder()
                .id(1L)
                .nome("Ailton")
                .salario(BigDecimal.valueOf(1300))
                .build();
    }


}
