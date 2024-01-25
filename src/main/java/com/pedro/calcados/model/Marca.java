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
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "marca")
public class Marca {
  @Id
  @SequenceGenerator(name = "marcas_sequence", sequenceName = "marcas_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "marcas_sequence")
  private Long id;
  private String nome;

  @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Modelo> modelos;
}
