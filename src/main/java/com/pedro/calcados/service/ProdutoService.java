package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Cor;
import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.model.Produto;
import com.pedro.calcados.repository.CorRepository;
import com.pedro.calcados.repository.ModeloRepository;
import com.pedro.calcados.repository.ProdutoRepository;

@Service
public class ProdutoService {
  @Autowired
  private final ProdutoRepository produtoRepository;

  @Autowired
  private final ModeloRepository modeloRepository;

  @Autowired
  private final CorRepository corRepository;

  public ProdutoService(
      ProdutoRepository produtoRepository,
      ModeloRepository modeloRepository,
      CorRepository corRepository) {
    this.produtoRepository = produtoRepository;
    this.modeloRepository = modeloRepository;
    this.corRepository = corRepository;
  }

  public List<Produto> listarProdutos() {
    return produtoRepository.findAll();
  }

  public Produto listarPorId(Long id) {
    return produtoRepository.findById(id).orElse(null);
  }

  public Produto criarProduto(Produto produto) {
    Long modeloId = produto.getModelo().getId();
    Modelo modelo = modeloRepository.findById(modeloId)
        .orElseThrow(() -> new IllegalStateException("O modelo com id " + modeloId + " não foi encontrado."));
    produto.setModelo(modelo);

    Long corId = produto.getCor().getId();
    Cor cor = corRepository.findById(corId).orElseThrow(() -> new IllegalStateException(
        "A cor com id " + corId + " não foi encontrada."));
    produto.setCor(cor);

    return produtoRepository.save(produto);
  }

  public void deletarProduto(Long id) {
    boolean exists = produtoRepository.existsById(id);

    if (!exists) {
      throw new IllegalStateException("O produto com id " + id + " não foi encontrado.");
    }

    produtoRepository.deleteById(id);
  }
}
