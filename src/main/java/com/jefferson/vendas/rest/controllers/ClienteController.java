package com.jefferson.vendas.rest.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	@RequestMapping(value = "/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public String hello(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s", nomeCliente);
	}
}
