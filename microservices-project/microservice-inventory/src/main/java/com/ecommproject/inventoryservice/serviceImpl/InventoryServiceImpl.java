package com.ecommproject.inventoryservice.serviceImpl;

import com.ecommproject.inventoryservice.dto.InventoryResponse;
import com.ecommproject.inventoryservice.repository.InventoryRepo;
import com.ecommproject.inventoryservice.services.InventoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows // throw the exception related to Thread.Sleep(),don't use in production
    public List<InventoryResponse> isInStock(List<String> skuCode) {
//      Added Sleep() method to help in execute timeout funtionality of resilience4j
//        log.info("Wait Started");
//        Thread.sleep(10000);
//        log.info("Wait Ended");
        return inventoryRepo
                .findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getProductQuantity() > 0)
                        .build()
                ).toList();
    }
}