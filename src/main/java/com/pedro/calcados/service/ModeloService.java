package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Categoria;
import com.pedro.calcados.model.Marca;
import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.repository.CategoriaRepository;
import com.pedro.calcados.repository.MarcaRepository;
import com.pedro.calcados.repository.ModeloRepository;

import jakarta.transaction.Transactional;

@Service
public class ModeloService {
  @Autowired
  private final ModeloRepository modeloRepository;

  @Autowired
  private final MarcaRepository marcaRepository;

  @Autowired
  private final CategoriaRepository categoriaRepository;

  public ModeloService(
      ModeloRepository modeloRepository,
      MarcaRepository marcaRepository,
      CategoriaRepository categoriaRepository) {
    this.modeloRepository = modeloRepository;
    this.marcaRepository = marcaRepository;
    this.categoriaRepository = categoriaRepository;
  }

  public List<Modelo> listarModelos() {
    return modeloRepository.findAll();
  }

  public Modelo listarPorId(Long id) {
    return modeloRepository.findById(id).orElse(null);
  }

  public List<Modelo> listarModelosPorMarca(Long marcaId) {
    return modeloRepository.findAllByMarcaId(marcaId);
  }

  public List<Modelo> listarModelosPorCategoria(Long categoriaId) {
    return modeloRepository.findByCategoriaId(categoriaId);
  }

  public List<Modelo> listarModelosPorMarca(String nome) {
    return modeloRepository.searchByMarcaLike(nome);
  }

  public Modelo salvarModelo(Modelo modelo) {
    Long categoriaId = modelo.getCategoria().getId();
    Categoria categoria = categoriaRepository.findById(
        categoriaId).orElseThrow(() -> new IllegalStateException("Categoria de id " + categoriaId + " não existe"));
    modelo.setCategoria(categoria);

    Long marcaId = modelo.getMarca().getId();
    Marca marca = marcaRepository.findById(marcaId)
        .orElseThrow(() -> new IllegalStateException("Marca de id " + marcaId + " não existe"));
    modelo.setMarca(marca);

    return modeloRepository.save(modelo);
  }

  @Transactional
  public void atualizarModelo(Long id, Modelo modeloRequest) {
    String nome = modeloRequest.getNome();
    Long marcaId = modeloRequest.getMarca() != null ? modeloRequest.getMarca().getId() : null;
    Long categoriaId = modeloRequest.getCategoria() != null ? modeloRequest.getCategoria().getId() : null;

    Modelo modeloEncontrado = modeloRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Modelo com id " + id + " não existe"));

    if (nome != null && nome.length() > 0 && !modeloEncontrado.getNome().equals(nome)) {
      modeloEncontrado.setNome(nome);
    }

    if (marcaId != null && marcaId > 0 && !modeloEncontrado.getMarca().getId().equals(marcaId)) {
      Marca marca = marcaRepository.findById(marcaId)
          .orElseThrow(() -> new IllegalStateException("A marca com id " + marcaId + " não existe"));
      modeloEncontrado.setMarca(marca);
    }

    if (categoriaId != null && categoriaId > 0 && !modeloEncontrado.getCategoria().getId().equals(categoriaId)) {
      Categoria categoria = categoriaRepository.findById(categoriaId)
          .orElseThrow(() -> new IllegalStateException("A categoria com id " + categoriaId + " não existe"));
      modeloEncontrado.setCategoria(categoria);
    }
  }

  public void deletarModelo(Long id) {
    modeloRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Modelo com id " + id + " não existe"));

    modeloRepository.deleteById(id);
  }
}
