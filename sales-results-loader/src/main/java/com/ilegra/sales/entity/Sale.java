package com.ilegra.sales.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sale extends Entity {
	private Integer id;
	private List<SaleItem> saleItems;
	private String salesmanName;
	
	public Sale(String registry, String registryDelimiter) {
		super.setType(EntityType.SALE);
		String [] data = registry.split(registryDelimiter);
		this.id = new Integer(data[1]);
		createSaleItems(data[2]);
		this.salesmanName = data[3];
	}

	private void createSaleItems(String itemsString) {
		String[] items = itemsString.split(",");
		saleItems = new ArrayList<>();
		for (String item : items) saleItems.add(SaleItem.createSaleItem(item));
	}
}
