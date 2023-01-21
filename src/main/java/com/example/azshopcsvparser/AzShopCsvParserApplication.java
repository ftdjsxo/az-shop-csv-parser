package com.example.azshopcsvparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AzShopCsvParserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AzShopCsvParserApplication.class, args);
	}
}
