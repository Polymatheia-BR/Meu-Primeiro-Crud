package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ClienteRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Cliente("John Peter", "john@gmail.com", "5581998765472")));
      log.info("Preloading " + repository.save(new Cliente("Peter Entero", "peter@gmail.com", "5581998764242")));
    };
  }
}