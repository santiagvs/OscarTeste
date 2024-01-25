package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

import com.pedro.calcados.model.Categoria;

@Service
public class CategoriaService {
  @Autowired
  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public Categoria listarPorId(Long id) {
    boolean exists = categoriaRepository.existsById(id);

    if (!exists) {
      throw new IllegalStateException("A categoria com id " + id + " não foi encontrada.");
    }

    return categoriaRepository.findById(id).orElse(null);
  }

  public List<Categoria> listarCategorias() {
    return categoriaRepository.findAll();
  }

  public Categoria salvarCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  @Transactional
  public void atualizarCategoria(Long id, String nome) {
    Categoria categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("A categoria com id " + id + " não existe"));

    if (nome != null && nome.length() > 0 && !nome.equals(categoria.getNome())) {
      categoria.setNome(nome);
    }
  }

  @Transactional
  public void deletarCategoria(Long id) {
    boolean exists = categoriaRepository.existsById(id);

    if (!exists) {
      throw new IllegalStateException("A categoria com id " + id + " não foi encontrada.");
    }

    categoriaRepository.deleteById(id);
  }
}
