package com.jefferson.vendas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.vendas.domain.entities.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
