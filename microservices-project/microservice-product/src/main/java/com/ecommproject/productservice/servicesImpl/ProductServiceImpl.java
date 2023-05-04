package com.ecommproject.productservice.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommproject.productservice.entities.Product;
import com.ecommproject.productservice.entityDto.ProductDto;
import com.ecommproject.productservice.repositories.ProductRepo;
import com.ecommproject.productservice.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public void addProduct(ProductDto productDto) {
		Product product = Product.builder()
				.productName(productDto.getProductName())
				.productSKU(productDto.getProductSKU())
				.productDescription(productDto.getProductDescription())
				.build();
		productRepo.save(product);
		log.info("Product {} is saved", product.getProductId(), product.getProductName());
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<ProductDto> productList = productRepo.findAll().stream().map(product -> maptoDto(product))
				.collect(Collectors.toList());
		return productList;
	}

//	Product to ProductDto conversion
	private ProductDto maptoDto(Product product) {
		ProductDto productDto = ProductDto.builder().productId(product.getProductId())
				.productName(product.getProductName()).productSKU(product.getProductSKU())
				.productDescription(product.getProductDescription()).build();
		return productDto;
	}

}
