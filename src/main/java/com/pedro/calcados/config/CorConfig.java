package com.pedro.calcados.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pedro.calcados.model.Cor;
import com.pedro.calcados.repository.CorRepository;

@Configuration
public class CorConfig {

  @Bean
  CommandLineRunner commandLineRunner(CorRepository repository) {
    return args -> {

      if (repository.findAll().size() == 0) {
        Cor preto = new Cor("Preto");
        Cor branco = new Cor("Branco");
        Cor vermelho = new Cor("Vermelho");
        Cor azul = new Cor("Azul");
        Cor verde = new Cor("Verde");
        Cor amarelo = new Cor("Amarelo");
        Cor cinza = new Cor("Cinza");
        Cor prata = new Cor("Prata");
        Cor dourado = new Cor("Dourado");
        Cor marrom = new Cor("Marrom");

        repository.saveAll(
            List.of(
                preto,
                branco,
                vermelho,
                azul,
                verde,
                amarelo,
                cinza,
                prata,
                dourado,
                marrom));
      }
    };
  }
}
