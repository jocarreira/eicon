package com.eicon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.eicon.jpa.entity.Pedido;
import com.eicon.jpa.repository.PedidoRepository;
import com.eicon.kafka.dto.LotePedidosDto;

@Component
public class LotePersistenceService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public void persistir(LotePedidosDto lote) {
		List<Pedido> pedidos = new ArrayList<>();
		lote.getPedidos().parallelStream().forEach(p -> {
			Pedido pedido = new Pedido();
			BeanUtils.copyProperties(p, pedido);
			pedidos.add(pedido);
		});
		this.persistir(pedidos);
	}
	
	private void persistir(List<Pedido> pedidos) {
		pedidoRepository.saveAll(pedidos);
	}
}
