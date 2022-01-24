package com.eicom.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.eicom.service.LogService;

@Component
@KafkaListener(id = "LogService", topics = { "EICON_SEND_LOG" })
public class LogConsumerListener {

	private final String GROUP_ID = LogService.class.getSimpleName();

	/**
	 * Consome email via listener
	 * @param email
	 */
	//@KafkaHandler
	//public void process(String log) {
	//	System.out.println("LOG Recebido : " + log);
	//}
	
	@KafkaHandler
	public void consumer(final ConsumerRecord consumerRecord) {
		System.out.println("LOG Recebido : " + consumerRecord.value());
	}
}
