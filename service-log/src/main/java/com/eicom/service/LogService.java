package com.eicom.service;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.eicon.kafka.KafkaConsumerService;

public class LogService {

	private final static String TOPIC = "EICON_SEND_LOG";
	
    //@Value("${topic.eicon.log.enviado}")
    //private static String TOPIC;
    
    public static void run() {
    	LogService logService = new LogService();
        try (KafkaConsumerService service = new KafkaConsumerService(LogService.class.getSimpleName(),
                Pattern.compile("EICON.*"),
                logService::parse,
                String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
            service.run();
        }
    }

    /**
     * Parseia a mensagem printa a mensagem de log
     * @param record
     */
    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("Consumindo log : ------------------------------------------");
        System.out.println("LOG: Topico: " + record.topic() + " Key : " + record.key() + 
        		" Value : " + record.value() + 
        		" Partition : " + record.partition() + 
        		" OffSet : " + record.offset());
    }
}
