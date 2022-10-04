package com.jefferson.vendas.rest.dto;

public class ItemPedidoDTO {
	private Integer produto;
	private Integer quantidade;
	
	public ItemPedidoDTO() { }

	public ItemPedidoDTO(Integer produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Integer getProduto() {
		return this.produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
