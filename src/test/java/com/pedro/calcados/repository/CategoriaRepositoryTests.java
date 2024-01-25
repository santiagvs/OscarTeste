package com.pedro.calcados.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pedro.calcados.model.Categoria;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoriaRepositoryTests {
  @Autowired
  private CategoriaRepository categoriaRepository;

  @Test
  public void CategoriaRepository_Save_ReturnsSavedCategoria() {
    // Arrange
    Categoria categoria = Categoria.builder().nome("Tênis").build();

    // Act
    Categoria savedCategoria = categoriaRepository.save(categoria);

    // Assert
    Assertions.assertThat(savedCategoria).isNotNull();
    Assertions.assertThat(savedCategoria.getId()).isGreaterThan(0);
  }

  @Test
  public void CategoriaRepository_FindAll_ReturnsListOfSavedCategorias() {
    // Arrange
    Categoria categoria1 = Categoria.builder().nome("Tênis").build();
    Categoria categoria2 = Categoria.builder().nome("Sapatos").build();
    categoriaRepository.save(categoria1);
    categoriaRepository.save(categoria2);

    // Act
    Iterable<Categoria> categorias = categoriaRepository.findAll();

    // Assert
    Assertions.assertThat(categorias).isNotEmpty();
    Assertions.assertThat(categorias).hasSize(2);
    Assertions.assertThat(categorias).element(0).hasFieldOrPropertyWithValue("nome", "Tênis");
  }
}
