
package com.jefferson.vendas.rest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jefferson.vendas.domain.entities.Usuario;
import com.jefferson.vendas.exceptions.SenhaInvalidaException;
import com.jefferson.vendas.rest.dto.CredenciaisDTO;
import com.jefferson.vendas.rest.dto.TokenDTO;
import com.jefferson.vendas.security.jwt.JwtService;
import com.jefferson.vendas.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario save(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);

		return service.save(usuario);
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			Usuario usuario = new Usuario(null, credenciais.getLogin(), credenciais.getSenha(), false);
			UserDetails usuarioAutenticado = service.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			
			return new TokenDTO(usuario.getLogin(), token);
			
		} catch (UsernameNotFoundException | SenhaInvalidaException ex) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
		}
	}
}
