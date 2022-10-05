package com.jefferson.vendas.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "descricao")
	@NotEmpty(message = "Campo Descrição é obrigatório.")
	private String descricao;
	
	@Column(name = "preco")
	@NotNull(message = "Campo Preço é obrigatório.")
	private BigDecimal preco;
	
	public Produto() { }
	
	public Produto(Integer id, String descricao, BigDecimal preco) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return this.preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
