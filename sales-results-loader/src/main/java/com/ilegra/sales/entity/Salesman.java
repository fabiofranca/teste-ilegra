package com.ilegra.sales.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Salesman extends Entity{
	
	private String cpf;
	private String name;
	private Double salary;

	public Salesman(String dataString, String delimiter) {
		super.setType(EntityType.SALESMAN);
		String [] data = dataString.split(delimiter);
		this.cpf = data[1];
		this.name = data[2];
		this.salary = new Double(data[3]);
	}
}
