package com.jefferson.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class InfoPedidoDTO {
	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private BigDecimal total;
	private String dataPedido;
	private List<InfoItemPedidoDTO> itens;
	
	public InfoPedidoDTO() { }

	

	public InfoPedidoDTO(Integer codigo, String cpf, String nomeCliente, BigDecimal total, String dataPedido,
			List<InfoItemPedidoDTO> itens) {
		this.codigo = codigo;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.total = total;
		this.dataPedido = dataPedido;
		this.itens = itens;
	}



	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	public List<InfoItemPedidoDTO> getItens() {
		return this.itens;
	}

	public void setItens(List<InfoItemPedidoDTO> itens) {
		this.itens = itens;
	}
}
