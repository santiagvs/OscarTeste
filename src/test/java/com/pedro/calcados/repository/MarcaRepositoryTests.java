package com.pedro.calcados.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pedro.calcados.model.Marca;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MarcaRepositoryTests {
  @Autowired
  private MarcaRepository marcaRepository;

  @Test
  public void MarcaRepository_Save_ReturnsSavedMarca() {
    // Arrange
    Marca marca = Marca.builder().nome("Nike").build();

    // Act
    Marca savedMarca = marcaRepository.save(marca);

    // Assert
    Assertions.assertThat(savedMarca).isNotNull();
    Assertions.assertThat(savedMarca.getId()).isGreaterThan(0);
  }

  @Test
  public void MarcaRepository_FindAll_ReturnsListOfMarcas() {
    // Arrange
    Marca marca1 = Marca.builder().nome("Nike").build();
    Marca marca2 = Marca.builder().nome("Adidas").build();
    marcaRepository.save(marca1);
    marcaRepository.save(marca2);

    // Act
    Iterable<Marca> marcas = marcaRepository.findAll();

    // Assert
    Assertions.assertThat(marcas).isNotEmpty();
    Assertions.assertThat(marcas).hasSize(2);
    Assertions.assertThat(marcas).element(0).hasFieldOrPropertyWithValue("nome", "Nike");
  }
}
