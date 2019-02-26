package com.ilegra.sales.processor.line;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ilegra.sales.entity.EntityType;
import com.ilegra.sales.entity.Sale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaleLineProcessor implements LineProcessor<Sale> {

	private static final String REGEX = "(003.)(\\d{2}.)(\\[.*?\\]).(\\w+)";
	
	private List<Sale> sales;
	@Override
	public List<Sale> processLine(String line) {
		identifySales(line);
		return sales;
	}
	
	private void identifySales(String line) {
		sales = new ArrayList<>();
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
		    String registry =  matcher.group(0);
		    log.debug("Sale found: {}", registry);
			sales.add(new Sale(registry, REGISTRY_DELIMITER));
		}
	}

	@Override
	public EntityType geEntityType() {
		return EntityType.SALE;
	}

}
