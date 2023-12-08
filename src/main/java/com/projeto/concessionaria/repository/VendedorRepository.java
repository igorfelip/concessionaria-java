package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Vendedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedores, Long> {
}
