package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.CarroCreator;
import com.projeto.concessionaria.util.ClienteCreator;

public class ClientePostRequestBodyCreator {
    public static ClientesPostRequestBody criaClientePostRequestBody() {
        return ClientesPostRequestBody.builder()
                .documento(ClienteCreator.criaClienteParaSerSalvo().getDocumento())
                .nome(ClienteCreator.criaClienteParaSerSalvo().getNome())
                .saldo(ClienteCreator.criaClienteParaSerSalvo().getSaldo())
                .build();
    }
}
