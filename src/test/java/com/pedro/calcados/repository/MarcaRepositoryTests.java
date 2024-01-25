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
    Marca marca = Marca.builder().nome("Nike").build();

    Marca savedMarca = marcaRepository.save(marca);

    Assertions.assertThat(savedMarca).isNotNull();
    Assertions.assertThat(savedMarca.getId()).isGreaterThan(0);
  }

  @Test
  public void MarcaRepository_FindAll_ReturnsListOfMarcas() {
    Marca marca1 = Marca.builder().nome("Nike").build();
    Marca marca2 = Marca.builder().nome("Adidas").build();
    marcaRepository.save(marca1);
    marcaRepository.save(marca2);

    Iterable<Marca> marcas = marcaRepository.findAll();

    Assertions.assertThat(marcas).isNotEmpty();
    Assertions.assertThat(marcas).hasSize(2);
    Assertions.assertThat(marcas).element(0).hasFieldOrPropertyWithValue("nome", "Nike");
  }

  @Test
  public void MarcaRepository_FindById_ReturnsMarcaById() {
    Marca marca = Marca.builder().nome("Puma").build();
    marcaRepository.save(marca);

    Marca marcaById = marcaRepository.findById(marca.getId()).get();

    Assertions.assertThat(marcaById).isNotNull();
  }
}
