package com.eicon.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eicon.jpa.entity.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
