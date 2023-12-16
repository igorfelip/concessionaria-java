package com.projeto.concessionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long documento;
    private BigDecimal saldo;
    @OneToMany
    @JoinColumn(name = "venda_id")
    private List<Carros> carrosComprados;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes)) return false;
        Clientes clientes = (Clientes) o;
        return Objects.equals(documento, clientes.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }
}
