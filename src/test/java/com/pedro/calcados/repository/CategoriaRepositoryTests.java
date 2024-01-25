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
    Categoria categoria = Categoria.builder().nome("Tênis").build();

    Categoria savedCategoria = categoriaRepository.save(categoria);

    // Assert
    Assertions.assertThat(savedCategoria).isNotNull();
    Assertions.assertThat(savedCategoria.getId()).isGreaterThan(0);
  }

  @Test
  public void CategoriaRepository_FindAll_ReturnsListOfSavedCategorias() {
    Categoria categoria1 = Categoria.builder().nome("Tênis").build();
    Categoria categoria2 = Categoria.builder().nome("Sapatos").build();
    categoriaRepository.save(categoria1);
    categoriaRepository.save(categoria2);

    Iterable<Categoria> categorias = categoriaRepository.findAll();

    Assertions.assertThat(categorias).isNotEmpty();
    Assertions.assertThat(categorias).hasSize(2);
    Assertions.assertThat(categorias).element(0).hasFieldOrPropertyWithValue("nome", "Tênis");
  }

  @Test
  public void CategoriaRepository_FindById_ReturnsCategoria() {
    Categoria categoria = Categoria.builder().nome("Tênis").build();
    categoriaRepository.save(categoria);

    Categoria foundCategoria = categoriaRepository.findById(categoria.getId()).get();

    Assertions.assertThat(foundCategoria).isNotNull();
    Assertions.assertThat(foundCategoria.getNome()).isEqualTo("Tênis");
  }

  @Test
  public void CategoriaRepository_Update_ReturnsUpdatedCategoria() {
    Categoria categoria = Categoria.builder().nome("Tênis").build();
    categoriaRepository.save(categoria);

    categoria.setNome("Sapatos");
    Categoria updatedCategoria = categoriaRepository.save(categoria);

    Assertions.assertThat(updatedCategoria).isNotNull();
    Assertions.assertThat(updatedCategoria.getNome()).isEqualTo("Sapatos");
  }

  @Test
  public void CategoriaRepository_Delete_RemovesCategoria() {
    Categoria categoria = Categoria.builder().nome("Tênis").build();
    categoriaRepository.save(categoria);

    categoriaRepository.delete(categoria);

    Assertions.assertThat(categoriaRepository.findById(categoria.getId())).isEmpty();
  }

}
