package com.eicom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eicom.service.LogService;

@SpringBootApplication
public class ServiceLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceLogApplication.class, args);
	}

}
