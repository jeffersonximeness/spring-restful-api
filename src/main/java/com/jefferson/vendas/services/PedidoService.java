package com.jefferson.vendas.services;

import java.util.Optional;

import com.jefferson.vendas.domain.entities.Pedido;
import com.jefferson.vendas.domain.enums.StatusPedido;
import com.jefferson.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido save(PedidoDTO peditoDto);
	Optional<Pedido> getPedidoById(Integer id);
	void atualizarStatus(Integer id, StatusPedido statusPedido);
}
