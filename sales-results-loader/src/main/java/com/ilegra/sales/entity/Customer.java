package com.ilegra.sales.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends Entity {
	
	private String cnpj;
	private String name;
	private String businessArea;
	
	public Customer(String registry, String delimiter) {
		super.setType(EntityType.CUSTOMER);
		String [] data = registry.split(delimiter);
		this.cnpj = data[1];
		this.name = data[2];
		this.businessArea = data[3];
	}
}
