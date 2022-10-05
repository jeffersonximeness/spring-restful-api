package com.jefferson.vendas.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jefferson.vendas.exceptions.RegraNegocioException;
import com.jefferson.vendas.rest.ApiErrors;
import com.jefferson.vendas.services.exceptions.PedidoNaoEncontradoException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String errorMessage = ex.getMessage();
		
		return new ApiErrors(errorMessage);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		String errorMessage = ex.getMessage();
		
		return new ApiErrors(errorMessage);
	}
}
