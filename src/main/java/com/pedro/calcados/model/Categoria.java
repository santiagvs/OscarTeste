package com.pedro.calcados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Id
  @SequenceGenerator(name = "categorias_sequence", sequenceName = "categorias_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categorias_sequence")
  private Long id;
  private String nome;

  public Categoria(Long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Long getId() {
    return this.id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
