package com.spring.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LojaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}
	
	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LojaApplication.class);
	}
	*/
}
