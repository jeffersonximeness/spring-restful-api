package com.jefferson.vendas.rest;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

	private List<String> errors;

	public ApiErrors(List<String> errorMessage) {
		this.errors = errorMessage;
	}
	
	public ApiErrors(String errorMessage) {
		this.errors = Arrays.asList(errorMessage);
	}
	
	public List<String> getErrors() {
		return this.errors;
	}
}
