package com.pjrminis.diecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DiecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiecastApplication.class, args);
	}

}
