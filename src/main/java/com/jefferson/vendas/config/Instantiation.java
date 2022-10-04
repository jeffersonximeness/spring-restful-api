package com.jefferson.vendas.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.entities.Produto;
import com.jefferson.vendas.domain.repositories.ClientesRepository;
import com.jefferson.vendas.domain.repositories.ProdutosRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	ClientesRepository clientesRepository;
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Override
	public void run(String... args) throws Exception {
		clientesRepository.save(new Cliente("Jefferson"));
		
		produtosRepository.save(new Produto(null, "Smart TV", new BigDecimal("1500")));
		produtosRepository.save(new Produto(null, "Mac Book", new BigDecimal("7500")));
		
		
	}

}
