package com.ecommproject.inventoryservice.controller;

import com.ecommproject.inventoryservice.dto.InventoryResponse;
import com.ecommproject.inventoryservice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

//	This get call is for single sku code
//	@GetMapping("/{sku-code}")
//	public boolean isInStock(@PathVariable("sku-code") String productSku) {
//		return inventoryService.isSkuAvailable(productSku);
//	}
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}