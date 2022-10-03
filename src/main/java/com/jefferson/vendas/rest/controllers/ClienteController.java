package com.jefferson.vendas.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.repositories.ClientesRepository;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClientesRepository repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cliente getClienteById(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.findById(id)
		.map(cliente -> {
			repository.delete(cliente);
			return Void.class;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		repository
		.findById(id)
		.map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			repository.save(cliente);
			return Void.class;
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")) ;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Cliente> example = Example.of(filtro, matcher);
		System.out.println(example);
		List<Cliente> lista = repository.findAll(example);
		
		return lista;
	}
}
