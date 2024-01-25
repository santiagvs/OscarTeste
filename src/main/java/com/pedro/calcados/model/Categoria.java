package com.pedro.calcados.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Modelo> modelos;

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

  public List<Modelo> getModelos() {
    return this.modelos;
  }

  public void setModelos(List<Modelo> modelos) {
    this.modelos = modelos;
  }
}
