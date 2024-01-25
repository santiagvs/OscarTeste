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
import org.springframework.web.bind.annotation.PutMapping;
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
    try {
      Cor cor = corService.listarPorId(id);

      return ResponseEntity.status(HttpStatus.OK).body(cor);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cor create(@RequestBody Cor cor) {
    return corService.salvarCor(cor);
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<?> update(
      @PathVariable("id") Long id,
      @RequestBody Cor cor) {
    try {
      corService.atualizarCor(id, cor.getNome());

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Cor de id %s foi atualizada com sucesso", id));

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
      corService.deletarCor(id);

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Cor de id %s deletada com sucesso", id));

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
