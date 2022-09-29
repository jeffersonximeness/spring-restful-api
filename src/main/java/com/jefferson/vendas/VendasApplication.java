package com.jefferson.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.entities.Pedido;
import com.jefferson.vendas.domain.repositories.ClientesRepository;
import com.jefferson.vendas.domain.repositories.PedidosRepository;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ClientesRepository clientesRepository,
			@Autowired PedidosRepository pedidosRepository) {
		
		return args -> {
			Cliente jefferson = new Cliente("Jefferson");
			Cliente james = new Cliente("James");
			
			clientesRepository.save(jefferson);
			clientesRepository.save(james);
		
			Pedido p = new Pedido();
			p.setCliente(jefferson);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidosRepository.save(p);
			

			Cliente cliente = clientesRepository.findClienteFetchPedidos(jefferson.getId());
			System.out.println(cliente);
			System.out.println(p);
			
			pedidosRepository.findByCliente(jefferson).forEach(pedido -> System.out.println(pedido));
;			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
