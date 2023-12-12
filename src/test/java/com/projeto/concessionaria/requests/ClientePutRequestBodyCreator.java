package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.ClienteCreator;

public class ClientePutRequestBodyCreator {
    public static ClientesPutRequestBody criaClientePutRequestBody() {
        return ClientesPutRequestBody.builder()
                .documento(ClienteCreator.criaClienteParaSerAtualizado().getDocumento())
                .saldo(ClienteCreator.criaClienteParaSerAtualizado().getSaldo())
                .nome(ClienteCreator.criaClienteParaSerAtualizado().getNome())
                .id(ClienteCreator.criaClienteParaSerAtualizado().getId())
                .build();
    }
}
