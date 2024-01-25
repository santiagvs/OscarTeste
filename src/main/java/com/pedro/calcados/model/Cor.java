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
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@Table(name = "cor")
public class Cor {
  @Id
  @SequenceGenerator(name = "cores_sequence", sequenceName = "cores_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cores_sequence")
  private Long id;
  private String nome;

  @OneToMany(mappedBy = "cor")
  @JsonIgnore
  private List<Produto> produtos;
}
