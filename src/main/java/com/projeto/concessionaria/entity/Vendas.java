package com.projeto.concessionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
