package com.eicon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eicon.jpa.repository.PedidoRepository;
import com.eicon.service.LoteProcessorService;

@EnableJpaRepositories
@SpringBootApplication
@ComponentScan(basePackages = { "com.eicon" })
@EntityScan(basePackages = { "com.eicon.jpa.entity" })
public class ServiceLoteProcessorApplication { 

	public static void main(String[] args) {
		SpringApplication.run(ServiceLoteProcessorApplication.class, args);
	}

}
