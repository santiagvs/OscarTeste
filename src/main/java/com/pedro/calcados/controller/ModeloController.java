package com.pedro.calcados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.service.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloController {
  private final ModeloService modeloService;

  @Autowired
  public ModeloController(ModeloService modeloService) {
    this.modeloService = modeloService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Modelo> list() {
    return modeloService.listarModelos();
  }

  // @GetMapping
  // public List<Modelo> listByMarca(@RequestParam Long marcaId) {
  // Marca marca = marcaRepository.findById(marcaId).orElseThrow(() -> new
  // ResourceNotFoundException("Marca não encontrada com o ID: " + marcaId));
  // }

  @GetMapping("/categoria")
  @ResponseStatus(HttpStatus.OK)
  public List<Modelo> listByCategoria(@RequestParam Long categoriaId) {
    return modeloService.listarModelosPorCategoria(categoriaId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Modelo> create(@RequestBody Modelo modelo) {
    Modelo modeloSalvo = modeloService.salvarModelo(modelo);
    return new ResponseEntity<>(modeloSalvo, HttpStatus.CREATED);
  }

}
