package com.ilegra.sales.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleItem {
	private String id;
	private Long quantity;
	private Double price;
	
	public static SaleItem createSaleItem(String itemData) {
		String[]item = itemData.replaceAll("]", "").split("-");
		return new SaleItem(item[0], new Long(item[1]), new Double(item[2]));
	}
}
