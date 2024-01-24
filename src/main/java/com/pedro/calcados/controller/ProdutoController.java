package com.pedro.calcados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.calcados.model.Produto;
import com.pedro.calcados.service.ProdutoService;

@RequestMapping("/produto")
@RestController
public class ProdutoController {
  @Autowired
  private final ProdutoService produtoService;

  public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  @GetMapping
  public List<Produto> list() {
    return produtoService.listarProdutos();
  }

  @GetMapping("{id}")
  public Produto get(@RequestParam Long id) {
    return produtoService.listarPorId(id);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Produto produto) {
    try {
      Produto produtoSalvo = produtoService.criarProduto(produto);

      return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      produtoService.deletarProduto(id);

      Map<String, String> response = new HashMap<>();
      response.put("message", String.format("Produto de id %s deletado com sucesso", id));

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (IllegalStateException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
