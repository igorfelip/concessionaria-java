package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.VendedorCreator;

public class VendedorPutRequestBodyCreator {
    public static VendedoresPutRequestBody criaVendedorPutRequestBody() {
        return VendedoresPutRequestBody.builder()
                .id(VendedorCreator.criaVendedorParaSerAtualizado().getId())
                .nome(VendedorCreator.criaVendedorParaSerAtualizado().getNome())
                .salario(VendedorCreator.criaVendedorParaSerAtualizado().getSalario())
                .build();
    }
}
