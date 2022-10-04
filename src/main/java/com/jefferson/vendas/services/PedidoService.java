package com.jefferson.vendas.services;

import com.jefferson.vendas.domain.entities.Pedido;
import com.jefferson.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido save(PedidoDTO peditoDto);
}
