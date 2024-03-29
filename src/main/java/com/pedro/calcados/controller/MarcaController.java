package com.pedro.calcados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pedro.calcados.model.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.pedro.calcados.service.MarcaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/marca")
public class MarcaController {
  @Autowired
  private final MarcaService marcaService;

  public MarcaController(MarcaService marcaService) {
    this.marcaService = marcaService;
  }

  @GetMapping
  public List<Marca> list() {
    return marcaService.listarMarcas();
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
    Marca marcaEncontrada = marcaService.listarPorId(id);

    if (marcaEncontrada == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca não encontrada");
    }

    return ResponseEntity.status(HttpStatus.OK).body(marcaEncontrada);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Marca marca) {
    try {
      Marca marcaSalva = marcaService.salvarMarca(marca);
      return new ResponseEntity<>(marcaSalva, HttpStatus.CREATED);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<?> update(
      @PathVariable("id") Long id,
      @RequestBody Marca marca) {
    try {
      marcaService.atualizarMarca(id, marca.getNome());

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Marca de id %s foi atualizada com sucesso", id));

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
    Marca marcaEncontrada = marcaService.listarPorId(id);
    if (marcaEncontrada == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca não encontrada");
    }

    marcaService.deletarMarca(marcaEncontrada);

    Map<String, String> response = new HashMap<>();
    response.put("message", "Marca removida e seus modelos associados também foram removidos com sucesso.");

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

}
