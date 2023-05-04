/**
 * 
 */
package com.ecommproject.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommproject.productservice.entityDto.ProductDto;

/**
 * @author Mohit
 *
 */
@Service
public interface ProductService {

	public void addProduct(ProductDto productDto);
	public List<ProductDto> getAllProduct();
}
