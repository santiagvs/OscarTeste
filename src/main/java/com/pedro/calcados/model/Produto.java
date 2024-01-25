package com.pedro.calcados.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "produto")
public class Produto {
  @Id
  @SequenceGenerator(name = "produtos_sequence", sequenceName = "produtos_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "produtos_sequence")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "modelo_id", nullable = false)
  private Modelo modelo;

  private Integer tamanho;

  private Double preco;

  @Column(name = "quantidade_estoque")
  private Long quantidadeEstoque;

  @ManyToOne
  @JoinColumn(name = "cor_id")
  private Cor cor;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "data_cadastro")
  private LocalDateTime dataCadastro;

  @PrePersist
  private void onCreate() {
    this.dataCadastro = LocalDateTime.now();
  }

  @JsonProperty("modelo_id")
  private void unpackModelo(Long modelo_id) {
    this.modelo = Modelo.builder().id(modelo_id).build();
  }

  @JsonProperty("cor_id")
  private void unpackCor(Long cor_id) {
    this.cor = Cor.builder().id(cor_id).build();
  }
}
