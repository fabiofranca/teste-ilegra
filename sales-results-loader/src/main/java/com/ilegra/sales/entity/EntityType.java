package com.ilegra.sales.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EntityType {
	
	SALESMAN("001"),
	CUSTOMER("002"),
	SALE("003");
	
	@Getter	private String type;

}
