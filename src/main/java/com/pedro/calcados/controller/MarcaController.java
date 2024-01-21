package com.pedro.calcados.controller;

import java.util.List;
import com.pedro.calcados.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.pedro.calcados.repository.MarcaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/marca")
public class MarcaController {
  private final MarcaRepository marcaRepository;

  @Autowired
  public MarcaController(MarcaRepository marcaRepository) {
    this.marcaRepository = marcaRepository;
  }

  @GetMapping()
  public List<Marca> list() {
    return marcaRepository.findAll();
  }

  // @GetMapping("/modelos")
  // public List<Marca> listWithModelos() {
  // return marcaRepository.findAllWithModelos();
  // }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Marca create(@RequestBody Marca marca) {
    marcaRepository.save(marca);
    return marca;
  }

}
