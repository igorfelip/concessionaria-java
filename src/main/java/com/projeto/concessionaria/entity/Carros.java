package com.projeto.concessionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carros {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private BigDecimal preco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carros)) return false;
        Carros carros = (Carros) o;
        return Objects.equals(marca, carros.marca) && Objects.equals(modelo, carros.modelo) && Objects.equals(ano, carros.ano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, ano);
    }
}
