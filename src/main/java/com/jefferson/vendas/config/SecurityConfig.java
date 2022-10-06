package com.jefferson.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("Jeff")
			.password(passwordEncoder().encode("123"))
			.roles("USER", "ADMIN");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests()
				.antMatchers("/api/clientes/**")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/produtos/**")
					.hasRole("ADMIN")
				.antMatchers("/api/pedidos/**")
					.hasAnyRole("USER", "ADMIN")
			.and()
				.httpBasic();
	}
}
