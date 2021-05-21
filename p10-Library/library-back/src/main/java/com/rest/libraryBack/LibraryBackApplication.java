package com.rest.libraryBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LibraryBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBackApplication.class, args);
	}

}
