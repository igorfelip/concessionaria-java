package com.projeto.concessionaria.repository;

import com.projeto.concessionaria.entity.Carros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrosRepository extends JpaRepository<Carros, Long> {


}
