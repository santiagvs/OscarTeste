package com.pedro.calcados.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelo")
public class Modelo {
  @Id
  @SequenceGenerator(name = "modelos_sequence", sequenceName = "modelos_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "modelos_sequence")
  private Long id;
  private String nome;

  @ManyToOne
  @JoinColumn(name = "marca_id", nullable = false)
  private Marca marca;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public Marca getMarca() {
    return marca;
  }

  // setters

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setMarca(Marca marca) {
    this.marca = marca;
  }

  @JsonProperty("marca_id")
  private void unpackMarca(Long marca_id) {
    this.marca = new Marca();
    this.marca.setId(marca_id);
  }
}
