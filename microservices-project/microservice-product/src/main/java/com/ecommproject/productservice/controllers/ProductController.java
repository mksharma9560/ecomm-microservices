package com.ecommproject.productservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ecommproject.productservice.entityDto.ProductDto;
import com.ecommproject.productservice.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@PostMapping("/add")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addProduct(@RequestBody ProductDto productDto) {
		productService.addProduct(productDto);
	}
	@GetMapping("/get")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProductDto> getAllProduct() {
		List<ProductDto> allProduct = productService.getAllProduct();
		return allProduct;
	}
}