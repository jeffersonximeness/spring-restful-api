package com.jefferson.vendas.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.vendas.domain.entities.Pedido;
import com.jefferson.vendas.rest.dto.PedidoDTO;
import com.jefferson.vendas.services.PedidoService;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO pedidoDto) {
		Pedido pedido = service.save(pedidoDto);
		
		return pedido.getId();
	}
}
