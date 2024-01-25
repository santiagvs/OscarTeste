package com.pedro.calcados.service;

import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedro.calcados.model.Cor;
import com.pedro.calcados.repository.CorRepository;

import jakarta.transaction.Transactional;

@Service
public class CorService {
  @Autowired
  private final CorRepository corRepository;

  public CorService(CorRepository corRepository) {
    this.corRepository = corRepository;
  }

  public List<Cor> listarCores() {
    return corRepository.findAll();
  }

  public Cor listarPorId(Long id) {
    boolean exists = corRepository.existsById(id);

    if (!exists) {
      throw new IllegalStateException("A cor com id " + id + " não foi encontrada.");
    }

    return corRepository.findById(id).orElse(null);
  }

  public Cor salvarCor(Cor cor) {
    String nomeNormalizado = Normalizer
        .normalize(cor.getNome().trim().toLowerCase(), Normalizer.Form.NFD)
        .replaceAll("[^\\p{ASCII}]", "");

    if (corRepository.existsByNomeIgnoreCase(nomeNormalizado)) {
      throw new IllegalStateException("A cor inserida já existe no nosso banco de dados.");
    }

    return corRepository.save(cor);
  }

  @Transactional
  public void atualizarCor(Long id, String nome) {
    Cor cor = corRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("A cor com id " + id + " não existe"));

    if (nome != null && nome.length() > 0 && !nome.equals(cor.getNome())) {
      cor.setNome(nome);
    }
  }

  public void deletarCor(Long id) {
    boolean exists = corRepository.existsById(id);

    if (!exists) {
      throw new IllegalStateException("A cor com id " + id + " não foi encontrada.");
    }

    corRepository.deleteById(id);
  }
}
