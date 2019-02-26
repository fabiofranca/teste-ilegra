package com.ilegra.sales;

import static com.ilegra.environment.Environment.registry;
import static com.ilegra.environment.Environment.retrieve;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.processor.Processor;
import com.ilegra.sales.load.SalesFileLoader;
import com.ilegra.sales.processor.SalesFileProcessor;
import com.ilegra.sales.processor.line.CustomerLineProcessor;
import com.ilegra.sales.processor.line.SaleLineProcessor;
import com.ilegra.sales.processor.line.SalesmanLineProcessor;

public class Bootstrap {
	
	public static void main(String[] args) throws Exception {
		registry(new CommonConfigurationsImpl());
		registry(new SalesFileLoader());
		registry(new SalesFileProcessor());
		
		
		SalesFileProcessor processor = (SalesFileProcessor) retrieve(Processor.class);
		processor.regitryProcessor(new SalesmanLineProcessor());
		processor.regitryProcessor(new CustomerLineProcessor());
 		processor.regitryProcessor(new SaleLineProcessor());
		
 		while(true)
 			processor.process();
	}

}
