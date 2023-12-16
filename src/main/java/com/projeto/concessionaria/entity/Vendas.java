package com.projeto.concessionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "carro_id")
    private Carros carro;
    @OneToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedores vendedor;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendas)) return false;
        Vendas vendas = (Vendas) o;
        return Objects.equals(carro, vendas.carro) && Objects.equals(vendedor, vendas.vendedor) && Objects.equals(cliente, vendas.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carro, vendedor, cliente);
    }
}
