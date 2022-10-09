package com.jefferson.vendas.exceptions;

public class SenhaInvalidaException extends RuntimeException {

	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}
