package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.CarroCreator;

public class CarroPutRequestBodyCreator {
    public static CarrosPutRequestBody criaCarroPutRequestBody() {
        return CarrosPutRequestBody.builder()
                .id(CarroCreator.criaCarroAtualizado().getId())
                .marca(CarroCreator.criaCarroAtualizado().getMarca())
                .preco(CarroCreator.criaCarroAtualizado().getPreco())
                .ano(CarroCreator.criaCarroAtualizado().getAno())
                .modelo(CarroCreator.criaCarroAtualizado().getModelo())
                .build();
    }
}