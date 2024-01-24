package com.pedro.calcados.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pedro.calcados.model.Modelo;
import com.pedro.calcados.service.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloController {
  @Autowired
  private final ModeloService modeloService;

  public ModeloController(ModeloService modeloService) {
    this.modeloService = modeloService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Modelo> list() {
    return modeloService.listarModelos();
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
    Modelo modeloEncontrado = modeloService.listarPorId(id);

    if (modeloEncontrado == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
    }

    return ResponseEntity.status(HttpStatus.OK).body(modeloEncontrado);
  }

  @GetMapping(params = "nome")
  public List<Modelo> listByMarca(@RequestParam String nome) {
    return modeloService.listarModelosPorMarca(nome);
  }

  @GetMapping(params = "categoriaId")
  @ResponseStatus(HttpStatus.OK)
  public List<Modelo> listByCategoria(@RequestParam Long categoriaId) {
    return modeloService.listarModelosPorCategoria(categoriaId);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Modelo modelo) {
    try {
      Modelo modeloSalvo = modeloService.salvarModelo(modelo);

      return new ResponseEntity<>(modeloSalvo, HttpStatus.CREATED);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<?> update(
      @PathVariable("id") Long id,
      @RequestBody Modelo modelo) {
    try {
      modeloService.atualizarModelo(id, modelo);

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Modelo de id %s atualizado com sucesso", id));

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      modeloService.deletarModelo(id);
      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Modelo de id %s deletado com sucesso", id));

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
