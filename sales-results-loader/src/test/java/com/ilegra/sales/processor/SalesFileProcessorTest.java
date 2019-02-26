package com.ilegra.sales.processor;

import static com.ilegra.environment.Environment.registry;
import static com.ilegra.environment.Environment.retrieve;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.processor.Processor;
import com.ilegra.sales.entity.Entity;
import com.ilegra.sales.entity.EntityType;
import com.ilegra.sales.load.SalesFileLoader;
import com.ilegra.sales.processor.line.CustomerLineProcessor;
import com.ilegra.sales.processor.line.SaleLineProcessor;
import com.ilegra.sales.processor.line.SalesmanLineProcessor;

public class SalesFileProcessorTest {
	
	public SalesFileProcessorTest() {
		registry(new CommonConfigurationsImpl());
		registry(new SalesFileLoader());
		registry(new SalesFileProcessor());
	}
	
	@Test
	public void shouldIdentifyRegistries() throws Exception {
		SalesFileProcessor processor = (SalesFileProcessor) retrieve(Processor.class);
		processor.regitryProcessor(new SalesmanLineProcessor());
		processor.regitryProcessor(new CustomerLineProcessor());
 		processor.regitryProcessor(new SaleLineProcessor());
		processor.process();
		HashMap<EntityType, List<Entity>> processedRegistries = processor.getRegistries();
		assertNotNull(processedRegistries.get(EntityType.SALESMAN));
		assertNotNull(processedRegistries.get(EntityType.CUSTOMER));
		assertNotNull(processedRegistries.get(EntityType.SALE));
	}
}
