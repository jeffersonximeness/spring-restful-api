package com.jefferson.vendas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.vendas.domain.entities.ItemPedido;

public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
