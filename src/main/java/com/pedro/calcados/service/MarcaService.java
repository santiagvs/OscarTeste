package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Marca;
import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.repository.MarcaRepository;
import com.pedro.calcados.repository.ModeloRepository;

@Service
public class MarcaService {
  private final ModeloRepository modeloRepository;
  private final MarcaRepository marcaRepository;

  @Autowired
  public MarcaService(MarcaRepository marcaRepository, ModeloRepository modeloRepository) {
    this.modeloRepository = modeloRepository;
    this.marcaRepository = marcaRepository;
  }

  public List<Marca> listarMarcas() {
    return marcaRepository.findAll();
  }

  public Marca salvarMarca(Marca marca) {
    List<Modelo> modelo = modeloRepository.findAllById(marca.getModelos().stream().map(Modelo::getId).toList());

    marca.setModelos(modelo);

    return marcaRepository.save(marca);
  }
}
