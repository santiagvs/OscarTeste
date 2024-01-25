package com.pedro.calcados.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "modelo")
public class Modelo {
  @Id
  @SequenceGenerator(name = "modelos_sequence", sequenceName = "modelos_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "modelos_sequence")
  private Long id;
  private String nome;

  @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Produto> produtos;

  @ManyToOne
  @JoinColumn(name = "marca_id", nullable = false)
  private Marca marca;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;

  @JsonProperty("marca_id")
  private void unpackMarca(Long marca_id) {
    this.marca = Marca.builder().id(marca_id).build();
  }

  @JsonProperty("categoria_id")
  private void unpackCategoria(Long categoria_id) {
    this.categoria = Categoria.builder().id(categoria_id).build();
  }
}
