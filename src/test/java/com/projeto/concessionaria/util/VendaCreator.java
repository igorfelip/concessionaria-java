package com.projeto.concessionaria.util;

import com.projeto.concessionaria.entity.Vendas;

public class VendaCreator {

      public static Vendas criaVendaValida() {
    return Vendas.builder()
            .id(1L)
            .vendedor(VendedorCreator.criaVendedorValido())
            .carro(CarroCreator.criaCarroValido())
            .cliente(ClienteCreator.criaClienteValido())
            .build();
    }
public static Vendas criaVendaParaSerSalva() {
    return Vendas.builder()
            .vendedor(VendedorCreator.criaVendedorValido())
            .carro(CarroCreator.criaCarroValido())
            .cliente(ClienteCreator.criaClienteValido())
            .build();
}}

