package com.eicom.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.eicom.service.LogService;
import com.eicon.kafka.GsonSerializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@EnableKafka
@Configuration
public class LogConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getSimpleName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonSerializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
