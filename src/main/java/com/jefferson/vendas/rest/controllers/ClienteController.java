package com.jefferson.vendas.rest.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.repositories.ClientesRepository;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClientesRepository repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente obj = repository.save(cliente);
		
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (cliente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		
		return repository
				.findById(id)
				.map(clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					repository.save(cliente);
					return ResponseEntity.noContent().build();
					
				}).orElseGet(() -> ResponseEntity.notFound().build()) ;
	}
}
