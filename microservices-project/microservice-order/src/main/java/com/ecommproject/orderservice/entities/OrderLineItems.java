package com.ecommproject.orderservice.entities;

import java.math.BigDecimal;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderLineId;
	private String orderLineSKU;
	private BigDecimal orderLinePrice;
	private Integer orderLineQuantity;
}
