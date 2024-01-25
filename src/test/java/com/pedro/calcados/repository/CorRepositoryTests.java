package com.pedro.calcados.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pedro.calcados.model.Cor;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CorRepositoryTests {
  @Autowired
  private CorRepository corRepository;

  @Test
  public void CorRepository_Save_ReturnsSavedCor() {
    Cor cor = Cor.builder().nome("Verde").build();

    Cor savedCor = corRepository.save(cor);

    Assertions.assertThat(savedCor).isNotNull();
    Assertions.assertThat(savedCor.getId()).isGreaterThan(0);
  }

  @Test
  public void CorRepository_FindAll_ReturnsListOfCores() {
    Cor cor1 = Cor.builder().nome("Verde").build();
    Cor cor2 = Cor.builder().nome("Azul").build();
    corRepository.save(cor1);
    corRepository.save(cor2);

    Iterable<Cor> cors = corRepository.findAll();

    Assertions.assertThat(cors).isNotEmpty();
    Assertions.assertThat(cors).hasSize(2);
    Assertions.assertThat(cors).element(0).hasFieldOrPropertyWithValue("nome", "Verde");
  }

  @Test
  public void CorRepository_FindById_ReturnsCorByIdNotNull() {
    Cor cor = Cor.builder().nome("Branco").build();
    corRepository.save(cor);

    Cor corById = corRepository.findById(cor.getId()).get();

    Assertions.assertThat(corById).isNotNull();
  }

  @Test
  public void CorRepository_FindByName_ReturnsCorByNomeNotNull() {
    Cor cor = Cor.builder().nome("Verde").build();
    corRepository.save(cor);

    Cor corByName = corRepository.findByNomeIgnoreCase("veRdE");

    Assertions.assertThat(corByName).isNotNull();
  }

  @Test
  public void CorRepository_UpdateCor_ReturnsCorNotNull() {
    Cor cor = Cor.builder().nome("Verde").build();
    corRepository.save(cor);

    Cor corSaved = corRepository.findById(cor.getId()).get();
    corSaved.setNome("Azul");

    Assertions.assertThat(corSaved).isNotNull();
    Assertions.assertThat(corSaved.getNome()).isEqualTo("Azul");
  }

  @Test
  public void CorRepository_DeleteCor_ReturnsCorNotFound() {
    Cor cor = Cor.builder().nome("Verde").build();
    corRepository.save(cor);

    corRepository.deleteById(cor.getId());

    Assertions.assertThat(corRepository.findById(cor.getId())).isEmpty();
  }
}
