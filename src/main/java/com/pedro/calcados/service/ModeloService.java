package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Marca;
import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.repository.MarcaRepository;
import com.pedro.calcados.repository.ModeloRepository;

@Service
public class ModeloService {
  private final ModeloRepository modeloRepository;
  private final MarcaRepository marcaRepository;

  @Autowired
  public ModeloService(ModeloRepository modeloRepository, MarcaRepository marcaRepository) {
    this.modeloRepository = modeloRepository;
    this.marcaRepository = marcaRepository;
  }

  public List<Modelo> listarModelos() {
    return modeloRepository.findAll();
  }

  public List<Modelo> listarModelosPorMarca(Long marcaId) {
    return modeloRepository.findAllByMarcaId(marcaId);
  }

  public Modelo salvarModelo(Modelo modelo) {
    Marca marca = marcaRepository.findById(modelo.getMarca().getId()).orElse(null);

    modelo.setMarca(marca);

    return modeloRepository.save(modelo);
  }
}
