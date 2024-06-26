package ru.mirea.auto_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AutoShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoShopApplication.class, args);
	}

}
