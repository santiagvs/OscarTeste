package com.pedro.calcados.specification;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.pedro.calcados.model.Produto;

public class ProdutoSpecifications {

  public static Specification<Produto> porTamanho(Integer tamanho) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tamanho"), tamanho);
  }

  public static Specification<Produto> porCategoria(String categoria) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("modelo").get("categoria").get("nome"),
        categoria);
  }

  public static Specification<Produto> porCor(Long corId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cor").get("id"), corId);
  }

  public static Specification<Produto> porPreco(Double precoMin, Double precoMax) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("preco"), precoMin, precoMax);
  }

  public static Specification<Produto> porMarca(String marca) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(
        criteriaBuilder.lower(root.get("modelo").get("marca").get("nome")),
        "%" + marca.trim().toLowerCase() + "%");
  }

  public static Specification<Produto> porDataCadastro(LocalDateTime dataCadastro) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dataCadastro"),
        dataCadastro);
  }

  public static Specification<Produto> porNomeModelo(String nomeModelo) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("modelo").get("nome")),
        "%" + nomeModelo.trim().toLowerCase() + "%");
  }

}
