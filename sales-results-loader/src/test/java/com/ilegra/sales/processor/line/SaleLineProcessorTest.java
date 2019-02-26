package com.ilegra.sales.processor.line;

import static com.ilegra.environment.Environment.registry;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.sales.entity.Sale;

public class SaleLineProcessorTest {

	private static final String ONLY_SALES = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n" + 
			"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";
	
	private static final Integer SALE_ID_1 = 10;
	private static final Integer SALE_ID_2 = 8;
	
	private LineProcessor<Sale> lineProcessor;
	
	public SaleLineProcessorTest() {
		registry(new CommonConfigurationsImpl());
	}
	
	@Before
	public void load() {
		lineProcessor = new SaleLineProcessor();
	}
	
	@Test
	public void shouldBuildSalesListFromDataString() {
		List<Sale> sales = buildListFromData(ONLY_SALES);
		Sale sale1 = (Sale) sales.get(0);
		Sale sale2 = (Sale) sales.get(1);
		assertEquals(SALE_ID_1, sale1.getId());
		assertEquals(SALE_ID_2, sale2.getId());
	}

	private List<Sale> buildListFromData(String dataToTest) {
		return lineProcessor.processLine(dataToTest);
	}
}
