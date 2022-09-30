package com.jefferson.vendas.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;
	
	public Cliente() { }
	
	public Cliente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}
	
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", pedidos=" + pedidos + "]";
	}
}
