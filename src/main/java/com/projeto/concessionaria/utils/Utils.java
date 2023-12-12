package com.projeto.concessionaria.utils;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.entity.Vendedores;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    private Utils() {
    }

    public static BigDecimal calculaComissao(Vendedores vendedores, Carros carros) {
        BigDecimal salario = vendedores.getSalario();
        BigDecimal comissionado = carros.getPreco().multiply(BigDecimal.valueOf(0.01));
        return salario.add(comissionado).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calculaParcelamento(Carros carro, Integer parcelas) {
        BigDecimal preco = carro.getPreco();
        BigDecimal valorParcial;
        BigDecimal valorParcela;
        switch (parcelas) {
            case 1:
                return preco;
            case 2:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.02));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 3:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.04));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(3).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 4:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.06));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(4).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 5:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.08));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 6:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.10));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(6).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 7:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.12));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(7).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 8:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.14));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(8).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 9:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.16));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(9).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 10:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.18));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 11:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.20));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(11).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            case 12:
                valorParcial = preco.multiply(BigDecimal.valueOf(0.22));
                valorParcela = (preco.add(valorParcial));
                return valorParcela.divide(BigDecimal.valueOf(12).setScale(2, RoundingMode.HALF_EVEN), RoundingMode.HALF_EVEN);
            default:
                return BigDecimal.valueOf(0.0);

        }

    }


}
