package com.pedro.calcados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
