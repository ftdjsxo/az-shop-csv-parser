package com.example.azshopcsvparser;

import com.example.azshopcsvparser.cache.Cachable;
import com.example.azshopcsvparser.cache.staticresources.Cache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class AzShopCsvParserApplication {

	public static void main(String[] args) {
		Cachable<String> stringCachable = new Cachable<>("https://www.google.it", new Date());
		Cache.putCache(stringCachable, "RedirectCacheName");
		ConfigurableApplicationContext context = SpringApplication.run(AzShopCsvParserApplication.class, args);
	}
}
