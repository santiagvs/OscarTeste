package com.pedro.calcados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedro.calcados.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
  List<Modelo> findAllByMarcaId(Long marcaId);

  @Query("SELECT m FROM Modelo m WHERE m.categoria.id = ?1")
  List<Modelo> findByCategoriaId(Long categoria_id);
}
