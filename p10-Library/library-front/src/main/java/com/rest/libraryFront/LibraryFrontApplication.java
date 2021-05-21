package com.rest.libraryFront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableFeignClients("com.rest.libraryFront")
public class LibraryFrontApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(LibraryFrontApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryFrontApplication.class);
	}
}
