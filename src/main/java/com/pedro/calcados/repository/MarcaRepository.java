package com.pedro.calcados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pedro.calcados.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
  boolean existsByNomeIgnoreCase(String nome);
}
