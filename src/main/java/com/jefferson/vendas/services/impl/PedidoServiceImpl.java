package com.jefferson.vendas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jefferson.vendas.domain.repositories.PedidosRepository;
import com.jefferson.vendas.services.PedidoService;

public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidosRepository repository;
}
