package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.ClienteCreator;

public class ClientePutRequestBodyCreator {
    public static ClientesPutRequestBody criaClientePutRequestBody() {
        return ClientesPutRequestBody.builder()
                .CPF(ClienteCreator.criaClienteParaSerAtualizado().getCPF())
                .saldo(ClienteCreator.criaClienteParaSerAtualizado().getSaldo())
                .nome(ClienteCreator.criaClienteParaSerAtualizado().getNome())
                .id(ClienteCreator.criaClienteParaSerAtualizado().getId())
                .build();
    }
}
