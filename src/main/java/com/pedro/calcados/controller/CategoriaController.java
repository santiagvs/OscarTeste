package com.pedro.calcados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.calcados.model.Categoria;
import com.pedro.calcados.service.CategoriaService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @GetMapping
  public List<Categoria> list() {
    return categoriaService.listarCategorias();
  }

  @GetMapping("{id}")
  public Categoria get(@PathVariable("id") Long id) {
    return categoriaService.listarPorId(id);
  }

  @PostMapping
  public Categoria create(@RequestBody Categoria categoria) {
    return categoriaService.salvarCategoria(categoria);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
    try {
      categoriaService.atualizarCategoria(id, categoria.getNome());

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Categoria de id %s foi atualizada com sucesso", id));

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      categoriaService.deletarCategoria(id);

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Categoria de id %s deletada com sucesso", id));

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
