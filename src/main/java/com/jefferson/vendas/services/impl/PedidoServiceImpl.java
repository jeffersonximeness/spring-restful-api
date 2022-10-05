package com.jefferson.vendas.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.entities.ItemPedido;
import com.jefferson.vendas.domain.entities.Pedido;
import com.jefferson.vendas.domain.entities.Produto;
import com.jefferson.vendas.domain.enums.StatusPedido;
import com.jefferson.vendas.domain.repositories.ClientesRepository;
import com.jefferson.vendas.domain.repositories.ItensPedidoRepository;
import com.jefferson.vendas.domain.repositories.PedidosRepository;
import com.jefferson.vendas.domain.repositories.ProdutosRepository;
import com.jefferson.vendas.exceptions.RegraNegocioException;
import com.jefferson.vendas.rest.dto.ItemPedidoDTO;
import com.jefferson.vendas.rest.dto.PedidoDTO;
import com.jefferson.vendas.services.PedidoService;
import com.jefferson.vendas.services.exceptions.PedidoNaoEncontradoException;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;
	
	@Autowired
	private ProdutosRepository produtosRepository;
	

	@Override
	@Transactional
	public Pedido save(PedidoDTO peditoDto) {
		Integer idCliente = peditoDto.getCliente();
		Cliente cliente = clientesRepository
							.findById(idCliente)
							.orElseThrow(() -> new RegraNegocioException("Código do cliente inválido!"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(peditoDto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		pedidosRepository.save(pedido);
		
		List<ItemPedido> itemPedido = convertItems(pedido, peditoDto.getItens());
		
		itensPedidoRepository.saveAll(itemPedido);
		pedido.setItens(itemPedido);
		
		return pedido;
	}
	
	private List<ItemPedido> convertItems(Pedido pedido, List<ItemPedidoDTO> items) {
		
		return items
				.stream()
				.map(dto -> {
					Integer idProduto = dto.getProduto();
					
					Produto produto = produtosRepository
							.findById(idProduto)
							.orElseThrow(() -> new RegraNegocioException("Código do produto inválido!"));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					
					return itemPedido;
										
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> getPedidoById(Integer id) {
		
		return pedidosRepository.findByIdFetchItens(id);
	}
	
	@Override
	@Transactional
	public void atualizarStatus(Integer id, StatusPedido statusPedido) {
		pedidosRepository
			.findById(id)
			.map(pedido -> {
				pedido.setStatus(statusPedido);
				return pedidosRepository.save(pedido);
			}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}
}
