package com.ilegra.sales.processor.line;

import static com.ilegra.environment.Environment.registry;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.sales.entity.Customer;

public class CustomerLineProcessorTest {

	private static final String CNPJ_CUSTOMER_2 = "2345675433444345";

	private static final String CNPJ_CUSTOMER_1 = "2345675434544345";

	private static final String ONLY_CUSTOMERS = "002ç2345675434544345çJose da SilvaçRural 002ç2345675433444345çEduardo PereiraçRural";

	private LineProcessor<Customer> lineProcessor;
	
	public CustomerLineProcessorTest() {
		registry(new CommonConfigurationsImpl());
	}
	
	@Before
	public void load() {
		lineProcessor = new CustomerLineProcessor();
	}
	
	@Test
	public void shouldBuildCustomerListFromDataString() {
		List<Customer> customers = buildListFromData(ONLY_CUSTOMERS);
		Customer customer1 = (Customer) customers.get(0);
		Customer customer2 = (Customer) customers.get(1);
		assertEquals(CNPJ_CUSTOMER_1, customer1.getCnpj());
		assertEquals(CNPJ_CUSTOMER_2, customer2.getCnpj());
	}
	
	private List<Customer> buildListFromData(String dataToTest){
		return lineProcessor.processLine(dataToTest);
	}

}
