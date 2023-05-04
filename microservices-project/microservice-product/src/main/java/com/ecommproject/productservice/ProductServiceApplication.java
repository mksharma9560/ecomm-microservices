package com.ecommproject.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		System.out.println("Datasource URL: " + System.getProperty("spring.datasource.url"));
		System.out.println("version: " + SpringVersion.getVersion());
		System.out.println("version: " + SpringBootVersion.getVersion());

	}
}