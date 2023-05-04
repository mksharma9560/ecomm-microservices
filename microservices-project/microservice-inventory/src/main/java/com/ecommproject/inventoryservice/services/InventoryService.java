package com.ecommproject.inventoryservice.services;

import com.ecommproject.inventoryservice.dto.InventoryResponse;
import com.ecommproject.inventoryservice.entities.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

	public List<InventoryResponse> isInStock(List<String> skuCode);
}
