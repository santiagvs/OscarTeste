package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Marca;
import com.pedro.calcados.repository.MarcaRepository;

@Service
public class MarcaService {
  private final MarcaRepository marcaRepository;

  @Autowired
  public MarcaService(MarcaRepository marcaRepository) {
    this.marcaRepository = marcaRepository;
  }

  public List<Marca> listarMarcas() {
    return marcaRepository.findAll();
  }

  public Marca listarPorId(Long id) {
    return marcaRepository.findById(id).orElse(null);
  }

  public Marca salvarMarca(Marca marca) {
    return marcaRepository.save(marca);
  }

  public void deletarMarca(Marca marca) {
    marcaRepository.delete(marca);
  }
}
