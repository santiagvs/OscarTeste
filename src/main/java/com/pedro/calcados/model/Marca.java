package com.pedro.calcados.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "marca")
public class Marca {
  @Id
  @SequenceGenerator(name = "marcas_sequence", sequenceName = "marcas_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "marcas_sequence")
  private Long id;
  private String nome;

  @OneToMany(mappedBy = "marca")
  @JsonIgnore
  private List<Modelo> modelos;

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Modelo> getModelos() {
    return modelos;
  }

  public void setModelos(List<Modelo> modelos) {
    this.modelos = modelos;
  }
}
