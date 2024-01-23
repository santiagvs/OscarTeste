package com.pedro.calcados.service;

import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Marca;
import com.pedro.calcados.repository.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
public class MarcaService {
  @Autowired
  private final MarcaRepository marcaRepository;

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
    String nomeNormalizado = Normalizer
        .normalize(marca.getNome().toLowerCase(), Normalizer.Form.NFD)
        .replaceAll("[^\\p{ASCII}]", "");

    if (marcaRepository.existsByNomeIgnoreCase(nomeNormalizado)) {
      throw new IllegalStateException("A marca inserida já existe no nosso banco de dados.");
    }

    return marcaRepository.save(marca);
  }

  @Transactional
  public void atualizarMarca(Long id, String nome) {
    Marca marca = marcaRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("A marca com id " + id + " não existe"));

    if (nome != null && nome.length() > 0 && !nome.equals(marca.getNome())) {
      marca.setNome(nome);
    }
  }

  public void deletarMarca(Marca marca) {
    marcaRepository.delete(marca);
  }
}
