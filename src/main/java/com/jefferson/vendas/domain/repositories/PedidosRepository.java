package com.jefferson.vendas.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.vendas.domain.entities.Cliente;
import com.jefferson.vendas.domain.entities.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);
}
