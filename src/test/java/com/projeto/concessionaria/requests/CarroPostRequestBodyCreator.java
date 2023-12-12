package com.projeto.concessionaria.requests;

import com.projeto.concessionaria.util.CarroCreator;

public class CarroPostRequestBodyCreator {
    public static CarrosPostRequestBody criaCarroPostRequestBody() {
        return CarrosPostRequestBody.builder()
                .marca(CarroCreator.criaCarroParaSerSalvo().getMarca())
                .preco(CarroCreator.criaCarroParaSerSalvo().getPreco())
                .ano(CarroCreator.criaCarroParaSerSalvo().getAno())
                .modelo(CarroCreator.criaCarroParaSerSalvo().getModelo())
                .build();
    }
}
