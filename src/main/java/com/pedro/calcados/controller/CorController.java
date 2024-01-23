package com.pedro.calcados.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pedro.calcados.model.Cor;
import com.pedro.calcados.service.CorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/cor")
@RestController
public class CorController {
  @Autowired
  private final CorService corService;

  public CorController(CorService corService) {
    this.corService = corService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Cor> list() {
    return corService.listarCores();
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
    Cor corEncontrada = corService.listarPorId(id);

    if (corEncontrada == null) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "Cor não encontrada");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    return ResponseEntity.status(HttpStatus.OK).body(corEncontrada);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cor create(@RequestBody Cor cor) {
    return corService.salvarCor(cor);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    Cor corEncontrada = corService.listarPorId(id);

    if (corEncontrada == null) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "Cor não encontrada");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    corService.deletarCor(corEncontrada);

    Map<String, String> response = new HashMap<>();
    response.put("message", "Cor removida com sucesso");

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

}
