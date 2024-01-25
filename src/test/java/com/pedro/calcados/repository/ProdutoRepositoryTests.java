package com.pedro.calcados.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pedro.calcados.model.Categoria;
import com.pedro.calcados.model.Cor;
import com.pedro.calcados.model.Marca;
import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.model.Produto;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProdutoRepositoryTests {
  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private CorRepository corRepository;

  @Autowired
  private ModeloRepository modeloRepository;

  @Autowired
  private MarcaRepository marcaRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  private Produto produto;
  private Cor cor;
  private Modelo modelo;
  private Marca marca;
  private Categoria categoria;

  @BeforeEach
  public void setup() {
    categoria = categoriaRepository.save(Categoria.builder().nome("Tênis").build());
    marca = marcaRepository.save(Marca.builder().nome("Adidas").build());
    modelo = modeloRepository.save(Modelo.builder().marca(marca).categoria(categoria).nome("Ultraboost").build());
    cor = corRepository.save(Cor.builder().nome("Azul").build());
    produto = Produto.builder()
        .modelo(modelo)
        .cor(cor)
        .tamanho(40)
        .preco(249.90)
        .quantidadeEstoque(10L)
        .build();
  }

  @Test
  public void ProdutoRepository_Save_ReturnsSavedProduto() {
    Produto savedProduto = produtoRepository.save(produto);

    Assertions.assertThat(savedProduto).isNotNull();
    Assertions.assertThat(savedProduto.getId()).isNotNull();
    Assertions.assertThat(savedProduto.getId()).isGreaterThan(0);
  }

  @Test
  public void ProdutoRepository_FindAll_ReturnsListOfProdutos() {
    Produto produto2 = Produto.builder()
        .modelo(modelo)
        .cor(cor)
        .tamanho(36)
        .preco(209.90)
        .quantidadeEstoque(28L)
        .build();

    produtoRepository.saveAll(List.of(produto, produto2));

    Iterable<Produto> produtos = produtoRepository.findAll();

    Assertions.assertThat(produtos).isNotEmpty();
    Assertions.assertThat(produtos).hasSize(2);
    Assertions.assertThat(produtos).element(0).hasFieldOrPropertyWithValue("modelo", produto.getModelo());
  }

  @Test
  public void ProdutoRepository_FindById_ReturnsProduto() {
    produtoRepository.save(produto);

    Produto foundProduto = produtoRepository.findById(produto.getId()).orElse(null);

    Assertions.assertThat(foundProduto).isNotNull();
    Assertions.assertThat(foundProduto.getId()).isEqualTo(produto.getId());
    Assertions.assertThat(foundProduto.getCor().getId()).isEqualTo(produto.getCor().getId());
  }

  @Test
  public void ProdutoRepository_FindById_ReturnsNull() {
    Produto foundProduto = produtoRepository.findById(1L).orElse(null);

    Assertions.assertThat(foundProduto).isNull();
  }

  @Test
  public void ProdutoRepository_UpdateProduto_ReturnsProdutoNotNull() {
    produtoRepository.save(produto);

    produto.setTamanho(42);
    Produto updatedProduto = produtoRepository.save(produto);

    Assertions.assertThat(updatedProduto).isNotNull();
    Assertions.assertThat(updatedProduto.getId()).isEqualTo(produto.getId());
    Assertions.assertThat(updatedProduto.getTamanho()).isEqualTo(produto.getTamanho());
  }

  @Test
  public void ProdutoRepository_FindByModeloId_ReturnsProdutoList() {
    Marca marcaNike = marcaRepository.save(Marca.builder().nome("Nike").build());
    Categoria categoriaSapatos = categoriaRepository.save(Categoria.builder().nome("Sapatos").build());
    Modelo modelo2 = modeloRepository
        .save(Modelo.builder().marca(marcaNike).categoria(categoriaSapatos).nome("Court Vision Low").build());

    Produto produto2 = Produto.builder()
        .cor(cor)
        .modelo(modelo2)
        .tamanho(36)
        .preco(199.90)
        .quantidadeEstoque(28L)
        .build();

    produtoRepository.saveAll(List.of(produto, produto2));

    Iterable<Produto> produtos = produtoRepository.findByModeloId(modelo2.getId());

    Assertions.assertThat(produtos).isNotEmpty();
    Assertions.assertThat(produtos).hasSize(1);
    Assertions.assertThat(produtos).element(0).hasFieldOrPropertyWithValue("modelo", modelo2);
  }

  @Test
  public void ProdutoRepository_FindByModeloId_ReturnsEmptyList() {
    Produto produto2 = Produto.builder()
        .cor(cor)
        .modelo(modelo)
        .tamanho(36)
        .preco(199.90)
        .quantidadeEstoque(28L)
        .build();

    produtoRepository.saveAll(List.of(produto, produto2));

    Iterable<Produto> produtos = produtoRepository.findByModeloId(2L);

    Assertions.assertThat(produtos).isEmpty();
  }

  @Test
  public void ProdutoRepository_DeleteProduto_ReturnsProdutoNotFound() {
    produtoRepository.save(produto);

    produtoRepository.delete(produto);

    Produto foundProduto = produtoRepository.findById(produto.getId()).orElse(null);
    Assertions.assertThat(foundProduto).isNull();
  }
}
