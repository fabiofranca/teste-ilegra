package com.ilegra.sales;

import static com.ilegra.environment.Environment.registry;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.sales.load.SalesFileLoader;
import com.ilegra.sales.processor.SalesFileProcessor;

public class Bootstrap {
	
	public static void main(String[] args) {
		registry(new CommonConfigurationsImpl());
		registry(new SalesFileLoader());
		registry(new SalesFileProcessor());
	}

}
