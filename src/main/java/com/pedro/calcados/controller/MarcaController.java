package com.pedro.calcados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pedro.calcados.model.Marca;
import com.pedro.calcados.model.Modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.pedro.calcados.service.MarcaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/marca")
public class MarcaController {
  private final MarcaService marcaService;

  @Autowired
  public MarcaController(MarcaService marcaService) {
    this.marcaService = marcaService;
  }

  @GetMapping()
  public List<Marca> list() {
    return marcaService.listarMarcas();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Marca create(@RequestBody Marca marca) {
    return marcaService.salvarMarca(marca);
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
