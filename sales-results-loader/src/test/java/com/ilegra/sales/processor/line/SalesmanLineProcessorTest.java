package com.ilegra.sales.processor.line;

import static com.ilegra.environment.Environment.registry;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.sales.entity.Salesman;

public class SalesmanLineProcessorTest {
	
	private static final String CPF_SALESMAN_2 = "3245678865434";
	private static final String CPF_SALESMAN_1 = "1234567891234";
	private final String ONLY_SALESMAN = "001ç1234567891234çDiegoç50000 001ç3245678865434çRenatoç40000.99";
	private final String SALESMAN_AND_CUSTOMER = "001ç1234567891234çDiegoç50000 002ç2345675434544345çJose da SilvaçRural";
	private LineProcessor<Salesman> lineProcessor;
	
	public SalesmanLineProcessorTest() {
		registry(new CommonConfigurationsImpl());
	}
	
	@Before
	public void load() {
		lineProcessor = new SalesmanLineProcessor();
	}
	
	@Test
	public void shouldBuildSalesmanListFromDataString() {
		List<Salesman> sallers = buildListFromData(ONLY_SALESMAN);
		Salesman salesman1 = (Salesman) sallers.get(0);
		Salesman salesman2 = (Salesman) sallers.get(1);
		assertEquals(CPF_SALESMAN_1, salesman1.getCpf());
		assertEquals(CPF_SALESMAN_2, salesman2.getCpf());
	}
	
	@Test
	public void shouldBuildOnlyOneSalesmanFromDataString() {
		List<Salesman> sallers = buildListFromData(SALESMAN_AND_CUSTOMER);
		Salesman salesman = sallers.get(0);
		assertEquals(1, sallers.size());
		assertEquals(CPF_SALESMAN_1, salesman.getCpf());
	}
	
	private List<Salesman> buildListFromData(String dataToTest){
		return lineProcessor.processLine(dataToTest);
	}
}
