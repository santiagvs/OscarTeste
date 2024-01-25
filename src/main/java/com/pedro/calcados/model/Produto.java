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

@Entity
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public Long getQuantidadeEstoque() {
    return quantidadeEstoque;
  }

  public void setQuantidadeEstoque(Long quantidadeEstoque) {
    this.quantidadeEstoque = quantidadeEstoque;
  }

  public Cor getCor() {
    return cor;
  }

  public void setCor(Cor cor) {
    this.cor = cor;
  }

  public Modelo getModelo() {
    return modelo;
  }

  public void setModelo(Modelo modelo) {
    this.modelo = modelo;
  }

  public Integer getTamanho() {
    return tamanho;
  }

  public void setTamanho(Integer tamanho) {
    this.tamanho = tamanho;
  }

  public LocalDateTime getDataCadastro() {
    return dataCadastro;
  }

  @PrePersist
  protected void onCreate() {
    this.dataCadastro = LocalDateTime.now();
  }

  public void setDataCadastro() {
    this.dataCadastro = LocalDateTime.now();
  }

  @JsonProperty("modelo_id")
  private void unpackModelo(Long modelo_id) {
    this.modelo = new Modelo();
    this.modelo.setId(modelo_id);
  }

  @JsonProperty("cor_id")
  private void unpackCor(Long cor_id) {
    this.cor = new Cor();
    this.cor.setId(cor_id);
  }
}
