package com.rest.libraryFront.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rest.libraryFront.exception.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {

	
	@Bean
	public CustomErrorDecoder mCustomErrorDecoder() {
		return new CustomErrorDecoder();
	}

}
