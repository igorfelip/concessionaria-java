package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.VendedorCreator;

public class VendedorPostRequestBodyCreator {
    public static VendedoresPostRequestBody criaVendedorPostRequestBody() {
        return VendedoresPostRequestBody.builder()
                .nome(VendedorCreator.criaVendedorParaSerSalvo().getNome())
                .salario(VendedorCreator.criaVendedorParaSerSalvo().getSalario())
                .build();
    }
}
