package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
}
