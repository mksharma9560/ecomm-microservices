package com.ecommproject.orderservice.entityDto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
	private long orderLineId;
	private String orderLineSKU;
	private BigDecimal orderLinePrice;
	private Integer orderLineQuantity;
}
