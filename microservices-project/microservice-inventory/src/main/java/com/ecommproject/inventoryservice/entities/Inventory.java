package com.ecommproject.inventoryservice.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String skuCode;
	private int productQuantity;

}
