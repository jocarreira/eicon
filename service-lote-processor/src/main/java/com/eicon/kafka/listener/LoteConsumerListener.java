package com.eicon.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.eicon.kafka.dto.LotePedidosDto;
import com.eicon.service.LoteProcessorService;
import com.google.gson.Gson;

@Component
@KafkaListener(id = "LoteProcessorService", topics = { "EICON_NOVOS_PEDIDOS" })
public class LoteConsumerListener {

	@Autowired
	private LoteProcessorService service;
	
	/**
	 * Consome Lote de Pedidos via listener
	 * @param email
	 */
	@KafkaHandler
	public void process(String record) {
		Gson gson = new Gson();
		LotePedidosDto lote = gson.fromJson(record, LotePedidosDto.class);
		System.out.println("LOTE Recebido : " + lote.toString());
		service.persistir(lote);
	}
}
