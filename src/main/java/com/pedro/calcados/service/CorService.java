package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedro.calcados.model.Cor;
import com.pedro.calcados.repository.CorRepository;

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
    return corRepository.findById(id).orElse(null);
  }

  public Cor salvarCor(Cor cor) {
    return corRepository.save(cor);
  }

  public void deletarCor(Cor cor) {
    corRepository.delete(cor);
  }
}
