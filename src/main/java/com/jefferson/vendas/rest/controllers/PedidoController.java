package com.jefferson.vendas.rest.controllers;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jefferson.vendas.domain.entities.ItemPedido;
import com.jefferson.vendas.domain.entities.Pedido;
import com.jefferson.vendas.domain.enums.StatusPedido;
import com.jefferson.vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import com.jefferson.vendas.rest.dto.InfoItemPedidoDTO;
import com.jefferson.vendas.rest.dto.InfoPedidoDTO;
import com.jefferson.vendas.rest.dto.PedidoDTO;
import com.jefferson.vendas.services.PedidoService;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid PedidoDTO pedidoDto) {
		Pedido pedido = service.save(pedidoDto);
		
		return pedido.getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public InfoPedidoDTO getById(@PathVariable Integer id) {
		return service
				.getPedidoById(id)
				.map(p -> convertPedidoToDTO(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		
		service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InfoPedidoDTO convertPedidoToDTO(Pedido pedido) {
		Integer codigo = pedido.getId();
		String dataPedido = pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String cpf = pedido.getCliente().getCpf();
		String nomeCliente = pedido.getCliente().getNome();
		BigDecimal total = pedido.getTotal();
		String status = pedido.getStatus().name();
		List<InfoItemPedidoDTO> itens = convertItemPedidoToDTO(pedido.getItens());
		
		return new InfoPedidoDTO(codigo, cpf, nomeCliente, total, dataPedido, status, itens);
	}
	
	private List<InfoItemPedidoDTO> convertItemPedidoToDTO(List<ItemPedido> itens) {
		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens
				.stream()
				.map(item -> {
					String descricao = item.getProduto().getDescricao();
					BigDecimal precoUnitario = item.getProduto().getPreco();
					Integer quantidade = item.getQuantidade();
					
					return new InfoItemPedidoDTO(descricao, precoUnitario, quantidade);
				}).collect(Collectors.toList());
	}
}
