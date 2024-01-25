package com.pedro.calcados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pedro.calcados.model.Cor;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {
  boolean existsByNomeIgnoreCase(String nome);

  Cor findByNomeIgnoreCase(String nome);
}
