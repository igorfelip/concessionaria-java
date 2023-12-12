package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.CarroCreator;
import com.projeto.concessionaria.util.ClienteCreator;
import com.projeto.concessionaria.util.VendedorCreator;

public class VendaPutRequestBodyCreator {
    public static VendasPutRequestBody criaVendaPutRequestBody() {
        return VendasPutRequestBody.builder()
                .id(1L)
                .carro(CarroCreator.criaCarroAtualizado())
                .cliente(ClienteCreator.criaClienteParaSerAtualizado())
                .vendedor(VendedorCreator.criaVendedorParaSerAtualizado())
                .build();
    }
}
