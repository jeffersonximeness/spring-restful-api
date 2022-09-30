package com.jefferson.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.repositories.ClientesRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	ClientesRepository clientesRepository;
	
	@Override
	public void run(String... args) throws Exception {
		clientesRepository.save(new Cliente("Jefferson"));
		
	}

}
