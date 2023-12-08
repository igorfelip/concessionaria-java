package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
}
