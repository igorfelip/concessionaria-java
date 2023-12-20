package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.ClienteCreator;

public class ClientePostRequestBodyCreator {
    public static ClientesPostRequestBody criaClientePostRequestBody() {
        return ClientesPostRequestBody.builder()
                .CPF(ClienteCreator.criaClienteParaSerSalvo().getCPF())
                .nome(ClienteCreator.criaClienteParaSerSalvo().getNome())
                .saldo(ClienteCreator.criaClienteParaSerSalvo().getSaldo())
                .build();
    }
}
