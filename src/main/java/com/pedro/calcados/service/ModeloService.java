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
    Categoria categoria = categoriaRepository.findById(modelo.getCategoria().getId()).orElse(null);
    Marca marca = marcaRepository.findById(modelo.getMarca().getId()).orElse(null);

    modelo.setCategoria(categoria);
    modelo.setMarca(marca);

    return modeloRepository.save(modelo);
  }

  public void deletarModelo(Modelo modelo) {
    modeloRepository.delete(modelo);
  }
}
