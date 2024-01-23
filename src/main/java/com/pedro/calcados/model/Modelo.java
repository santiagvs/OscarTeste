package com.pedro.calcados.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @OneToMany(mappedBy = "modelo")
  @JsonIgnore
  private List<Produto> produtos;

  @ManyToOne
  @JoinColumn(name = "marca_id", nullable = false)
  private Marca marca;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Marca getMarca() {
    return marca;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  // setters

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setMarca(Marca marca) {
    this.marca = marca;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @JsonProperty("marca_id")
  private void unpackMarca(Long marca_id) {
    this.marca = new Marca();
    this.marca.setId(marca_id);
  }

  @JsonProperty("categoria_id")
  private void unpackCategoria(Long categoria_id) {
    this.categoria = new Categoria();
    this.categoria.setId(categoria_id);
  }
}
