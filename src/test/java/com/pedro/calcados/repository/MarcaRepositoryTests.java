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
    Marca marca = new Marca();
    marca.setNome("Nike");

    // Act
    Marca savedMarca = marcaRepository.save(marca);

    // Assert
    Assertions.assertThat(savedMarca).isNotNull();
    Assertions.assertThat(savedMarca.getId()).isGreaterThan(0);
  }
}
