package com.jefferson.vendas.services.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException() {
		super("Pedido Não Encontrado!");
	}
}
