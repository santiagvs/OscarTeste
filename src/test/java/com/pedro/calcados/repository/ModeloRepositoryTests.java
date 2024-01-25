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
import com.pedro.calcados.model.Marca;
import com.pedro.calcados.model.Modelo;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ModeloRepositoryTests {
  @Autowired
  private ModeloRepository modeloRepository;

  @Autowired
  private MarcaRepository marcaRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  private Marca marca;
  private Categoria categoria;

  @BeforeEach
  public void setup() {
    marca = marcaRepository.save(Marca.builder().nome("Nike").build());
    categoria = categoriaRepository.save(Categoria.builder().nome("Tênis").build());

  }

  @Test
  public void ModeloRepository_Save_ReturnsSavedModelo() {
    Modelo modelo = Modelo.builder().nome("Air Force 1").marca(marca).categoria(categoria).build();

    Modelo savedModelo = modeloRepository.save(modelo);

    Assertions.assertThat(savedModelo).isNotNull();
    Assertions.assertThat(savedModelo.getId()).isNotNull();
    Assertions.assertThat(savedModelo.getId()).isGreaterThan(0);
  }

  @Test
  public void ModeloRepository_FindAll_ReturnsListOfModelos() {
    Modelo modelo1 = Modelo.builder().nome("Air Force 1").marca(marca).categoria(categoria).build();
    Modelo modelo2 = Modelo.builder().nome("Downshifter").marca(marca).categoria(categoria).build();
    modeloRepository.save(modelo1);
    modeloRepository.save(modelo2);

    Iterable<Modelo> modelos = modeloRepository.findAll();

    Assertions.assertThat(modelos).isNotEmpty();
    Assertions.assertThat(modelos).hasSize(2);
    Assertions.assertThat(modelos).element(0).hasFieldOrPropertyWithValue("nome", "Air Force 1");
  }

  @Test
  public void ModeloRepository_FindById_ReturnsModelo() {
    Modelo modelo = Modelo.builder().nome("Air Force 1").marca(marca).categoria(categoria).build();
    modeloRepository.save(modelo);

    Modelo foundModelo = modeloRepository.findById(modelo.getId()).orElse(null);

    Assertions.assertThat(foundModelo).isNotNull();
    Assertions.assertThat(foundModelo.getId()).isEqualTo(modelo.getId());
    Assertions.assertThat(foundModelo.getNome()).isEqualTo(modelo.getNome());
  }

  @Test
  public void ModeloRepository_FindById_ReturnsNull() {
    Modelo foundModelo = modeloRepository.findById(1L).orElse(null);

    Assertions.assertThat(foundModelo).isNull();
  }

  @Test
  public void ModeloRepository_UpdateModelo_ReturnsModeloNotNull() {
    Modelo modelo = Modelo.builder().nome("Air Force 1").marca(marca).categoria(categoria).build();
    modeloRepository.save(modelo);

    modelo.setNome("Downshifter");
    Modelo updatedModelo = modeloRepository.save(modelo);

    Assertions.assertThat(updatedModelo).isNotNull();
    Assertions.assertThat(updatedModelo.getId()).isEqualTo(modelo.getId());
    Assertions.assertThat(updatedModelo.getNome()).isEqualTo(modelo.getNome());
  }

  @Test
  public void ModeloRepository_SearchesByMarcaLike_ReturnsListOfModelos() {
    Marca marcaAdidas = marcaRepository.save(Marca.builder().nome("Adidas").build());
    Modelo modelo1 = Modelo.builder().nome("Grand Court").marca(marcaAdidas).categoria(categoria).build();
    Modelo modelo2 = Modelo.builder().nome("Downshifter").marca(marca).categoria(categoria).build();
    Modelo modelo3 = Modelo.builder().nome("Ultraboost").marca(marcaAdidas).categoria(categoria).build();

    modeloRepository.saveAll(List.of(modelo1, modelo2, modelo3));

    Iterable<Modelo> modelos = modeloRepository.searchByMarcaLike("adidas");

    Assertions.assertThat(modelos).isNotEmpty();
    Assertions.assertThat(modelos).hasSize(2);
    Assertions.assertThat(modelos).element(0).hasFieldOrPropertyWithValue("nome", "Grand Court");
    Assertions.assertThat(modelos).element(1).hasFieldOrPropertyWithValue("nome", "Ultraboost");
  }

  @Test
  public void ModeloRepository_SearchesByMarcaLike_ReturnsEmptyList() {
    Modelo modelo1 = Modelo.builder().nome("Revolution").marca(marca).categoria(categoria).build();
    Modelo modelo2 = Modelo.builder().nome("Downshifter").marca(marca).categoria(categoria).build();

    modeloRepository.saveAll(List.of(modelo1, modelo2));

    Iterable<Modelo> modelos = modeloRepository.searchByMarcaLike("adidas");

    Assertions.assertThat(modelos).isEmpty();
  }

  @Test
  public void ModeloRepository_FindByCategoriaId_ReturnsModeloList() {
    Categoria categoriaSapatos = categoriaRepository.save(Categoria.builder().nome("Sapatos").build());
    Modelo modelo1 = Modelo.builder().nome("Revolution").marca(marca).categoria(categoria).build();
    Modelo modelo2 = Modelo.builder().nome("Court Legacy").marca(marca).categoria(categoriaSapatos).build();

    modeloRepository.saveAll(List.of(modelo1, modelo2));

    Iterable<Modelo> modelos = modeloRepository.findByCategoriaId(categoriaSapatos.getId());

    Assertions.assertThat(modelos).isNotEmpty();
    Assertions.assertThat(modelos).hasSize(1);
    Assertions.assertThat(modelos).element(0).hasFieldOrPropertyWithValue("nome", "Court Legacy");
  }

  @Test
  public void ModeloRepository_FindByCategoriaId_ReturnsEmptyList() {
    Categoria categoriaSapatos = categoriaRepository.save(Categoria.builder().nome("Sapatos").build());
    Modelo modelo1 = Modelo.builder().nome("Court Vision Low").marca(marca).categoria(categoriaSapatos).build();
    Modelo modelo2 = Modelo.builder().nome("Court Legacy").marca(marca).categoria(categoriaSapatos).build();

    modeloRepository.saveAll(List.of(modelo1, modelo2));

    Iterable<Modelo> modelos = modeloRepository.findByCategoriaId(categoria.getId());

    Assertions.assertThat(modelos).isEmpty();
  }

  @Test
  public void ModeloRepository_DeleteModelo_ReturnsModeloNotFound() {
    Modelo modelo = Modelo.builder().nome("Air Force 1").marca(marca).categoria(categoria).build();
    modeloRepository.save(modelo);

    modeloRepository.delete(modelo);

    Modelo foundModelo = modeloRepository.findById(modelo.getId()).orElse(null);
    Assertions.assertThat(foundModelo).isNull();
  }
}
