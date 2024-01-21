package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.repository.CategoriaRepository;
import com.pedro.calcados.model.Categoria;

@Service
public class CategoriaService {
  private final CategoriaRepository categoriaRepository;

  @Autowired
  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public List<Categoria> listarCategorias() {
    return categoriaRepository.findAll();
  }

  public Categoria salvarCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }
}
