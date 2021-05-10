package com.spring.loja;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		// WebMvcConfigurer.super.addResourceHandlers(registry);

		Path produtoUploadDir = Paths.get("images");
		String produtoUploadPath = produtoUploadDir.toFile().getAbsolutePath();

		registry.addResourceHandler("/images/**").addResourceLocations("file:/" + produtoUploadPath + "/");
	}

}
