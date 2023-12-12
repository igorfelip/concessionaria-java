package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.CarroCreator;
import com.projeto.concessionaria.util.ClienteCreator;
import com.projeto.concessionaria.util.VendedorCreator;

public class VendaPostRequestBodyCreator {
    public static VendasPostRequestBody criaVendaPostRequestBody() {
        return VendasPostRequestBody.builder()
                .carro(CarroCreator.criaCarroValido())
                .cliente(ClienteCreator.criaClienteValido())
                .vendedor(VendedorCreator.criaVendedorValido())
                .build();
    }
}
