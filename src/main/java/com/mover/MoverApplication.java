package com.mover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MoverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoverApplication.class, args);
	}

}
