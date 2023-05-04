package com.ecommproject.orderservice.services;

import org.springframework.stereotype.Service;

import com.ecommproject.orderservice.entityDto.OrderRequest;

@Service
public interface OrderService {

	public String createOrder(OrderRequest orderRequest);

}