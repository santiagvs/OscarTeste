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
        Cor preto = Cor.builder().nome("Preto").build();
        Cor branco = Cor.builder().nome("Branco").build();
        Cor vermelho = Cor.builder().nome("Vermelho").build();
        Cor azul = Cor.builder().nome("Azul").build();
        Cor verde = Cor.builder().nome("Verde").build();
        Cor amarelo = Cor.builder().nome("Amarelo").build();
        Cor cinza = Cor.builder().nome("Cinza").build();
        Cor prata = Cor.builder().nome("Prata").build();
        Cor dourado = Cor.builder().nome("Dourado").build();
        Cor marrom = Cor.builder().nome("Marrom").build();

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
