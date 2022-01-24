package com.eicon.components;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.eicon.controller.LotePedidosController;

@Configuration
@ComponentScan(basePackageClasses= {LotePedidosController.class})
public class AppConfiguration {

	/*
	@Bean
	public MessageSource messageSource() throws IOException{

	    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
	    propertiesFactoryBean.setLocation(new ClassPathResource("messages.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCommonMessages(propertiesFactoryBean.getObject());
	    return messageSource;

	}
	*/
	
}
