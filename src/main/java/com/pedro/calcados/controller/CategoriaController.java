package com.pedro.calcados.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.calcados.model.Categoria;
import com.pedro.calcados.service.CategoriaService;

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

  @PostMapping
  public Categoria create(@RequestBody Categoria categoria) {
    return categoriaService.salvarCategoria(categoria);
  }
}
