package com.sakalou.checkapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * CheckappApplication
 */
@EnableCaching
@SpringBootApplication
public class CheckappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckappApplication.class, args);
	}

}
