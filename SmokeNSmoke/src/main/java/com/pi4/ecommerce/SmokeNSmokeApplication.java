package com.pi4.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.pi4.ecommerce.entity"})
public class SmokeNSmokeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmokeNSmokeApplication.class, args);
	}

}
