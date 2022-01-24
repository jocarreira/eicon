package com.eicon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.eicon.jpa.entity.Pedido;
import com.eicon.jpa.repository.PedidoRepository;
import com.eicon.kafka.KafkaConsumerService;
import com.eicon.kafka.dto.LotePedidosDto;

@Service
public class LoteProcessorService {

	private static final Logger logger = LoggerFactory.getLogger(LoteProcessorService.class);
	
	@Autowired
	PedidoRepository pedidoRepository;
	
    //@Value("${topic.eicon.novos.pedidos}")
    private static String TOPIC = "EICON_NOVOS_PEDIDOS";
    
    public static void run() {
    	LoteProcessorService processor = new LoteProcessorService();
        try (KafkaConsumerService service = new KafkaConsumerService<>(LoteProcessorService.class.getSimpleName(),
        		TOPIC,
                processor::parse,
                LotePedidosDto.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, LotePedidosDto> record) {
        System.out.println("------------------------------------------");
        System.out.println(">>>>> Consumindo novo lote...");
        System.out.println(">>>>>> Topico: " + record.topic() + " Key : " + record.key() + 
        		" Value : " + record.value() + 
        		" Partition : " + record.partition() + 
        		" OffSet : " + record.offset());
        try {
            LotePedidosDto lote = record.value();
        	System.out.println("Processando lote :  " + lote.toString());
        	this.persistir(lote);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Lote Processado.");
    }

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
