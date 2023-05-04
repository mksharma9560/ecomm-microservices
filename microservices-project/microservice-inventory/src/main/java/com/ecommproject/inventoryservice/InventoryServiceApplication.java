package com.ecommproject.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.ecommproject.inventoryservice.entities.Inventory;
import com.ecommproject.inventoryservice.repository.InventoryRepo;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo) {
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Iphone_13");
			inventory1.setProductQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("Iphone_13_red");
			inventory2.setProductQuantity(0);

			inventoryRepo.save(inventory1);
			inventoryRepo.save(inventory2);
		};
	}

}
