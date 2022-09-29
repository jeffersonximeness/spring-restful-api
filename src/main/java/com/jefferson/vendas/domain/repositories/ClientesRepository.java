package com.jefferson.vendas.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jefferson.vendas.domain.entities.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String string);
	
    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );
}
