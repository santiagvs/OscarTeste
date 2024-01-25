package com.pedro.calcados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pedro.calcados.model.Cor;
import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.model.Produto;
import com.pedro.calcados.repository.CorRepository;
import com.pedro.calcados.repository.ModeloRepository;
import com.pedro.calcados.repository.ProdutoRepository;
import com.pedro.calcados.specification.ProdutoSpecifications;

import jakarta.transaction.Transactional;

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

  public List<Produto> filtrarProdutos(Integer tamanho, String categoria, Long corId, Double precoMin,
      Double precoMax, String marca, String modelo) {
    Specification<Produto> spec = Specification.where(null);

    if (tamanho != null) {
      spec = spec.and(ProdutoSpecifications.porTamanho(tamanho));
    }

    if (categoria != null && !categoria.trim().isEmpty()) {
      spec = spec.and(ProdutoSpecifications.porCategoria(categoria.trim()));
    }

    if (corId != null) {
      spec = spec.and(ProdutoSpecifications.porCor(corId));
    }

    final Specification<Produto> finalSpec = spec;

    spec = Optional.ofNullable(precoMin)
        .map(min -> finalSpec
            .and(ProdutoSpecifications.porPreco(min, Optional.ofNullable(precoMax).orElse(Double.MAX_VALUE))))
        .orElse(spec);

    spec = Optional.ofNullable(precoMax)
        .map(max -> finalSpec.and(ProdutoSpecifications.porPreco(Optional.ofNullable(precoMin).orElse(0.0), max)))
        .orElse(spec);

    if (marca != null && !marca.trim().isEmpty()) {
      spec = spec.and(ProdutoSpecifications.porMarca(marca.trim()));
    }

    if (modelo != null && !modelo.trim().isEmpty()) {
      spec = spec.and(ProdutoSpecifications.porModelo(modelo.trim()));
    }

    return produtoRepository.findAll(spec);
  }

  public Produto listarPorId(Long id) {
    boolean exists = produtoRepository.existsById(id);

    if (!exists) {
      throw new IllegalStateException("O produto com id " + id + " não foi encontrado.");
    }

    return produtoRepository.findById(id).orElse(null);
  }

  @Transactional
  public void atualizarProduto(Long id, Produto produtoRequest) {
    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("O produto com id " + id + " não foi encontrado."));

    if (produtoRequest.getPreco() != null || produtoRequest.getPreco() < 0) {
      produto.setPreco(produtoRequest.getPreco());
    }

    if (produtoRequest.getQuantidadeEstoque() != null) {
      produto.setQuantidadeEstoque(produtoRequest.getQuantidadeEstoque());
    }

    if (produtoRequest.getTamanho() != null) {
      produto.setTamanho(produtoRequest.getTamanho());
    }

    if (produtoRequest.getModelo() != null && produtoRequest.getModelo().getId() != null) {
      Modelo modelo = modeloRepository.findById(produtoRequest.getModelo().getId())
          .orElseThrow(() -> new IllegalStateException(
              "O modelo com id " + produtoRequest.getModelo().getId() + " não foi encontrado."));
      produto.setModelo(modelo);
    }

    if (produtoRequest.getCor() != null && produtoRequest.getCor().getId() != null) {
      Cor cor = corRepository.findById(produtoRequest.getCor().getId())
          .orElseThrow(() -> new IllegalStateException(
              "A cor com id " + produtoRequest.getCor().getId() + " não foi encontrada."));
      produto.setCor(cor);
    }
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
