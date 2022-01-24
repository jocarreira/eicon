package com.eicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
@ComponentScan(basePackages = { "com.eicon.controller", "com.eicon.service"  })
@EntityScan(basePackages = { "com.eicon.controller.entity" })
public class ProcessorLotePedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorLotePedidoApplication.class, args);
	}

}
