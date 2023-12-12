package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Vendedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedoresRepository extends JpaRepository<Vendedores, Long> {
}
