package com.ecommproject.productservice.entityDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
	private int productId;
	private String productName;
	private int productSKU;
	private String productDescription;
}
