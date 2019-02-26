package com.ilegra.sales.processor.line;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ilegra.sales.entity.Customer;
import com.ilegra.sales.entity.EntityType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerLineProcessor implements LineProcessor<Customer> {

	private final String REGEX = "(002.)(\\d{16}.)([\\w\\s]+.)(\\w*.)";
	
	private List<Customer> customers;
	@Override
	public List<Customer> processLine(String line) {
		identify(line);
		return customers;
	}

	
	private void identify(String line) {
		customers = new ArrayList<>();
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
		    String registry =  matcher.group(0);
		    log.debug("Customer found: {}", registry);
			customers.add(new Customer(registry, REGISTRY_DELIMITER));
		}
	}

	@Override
	public EntityType geEntityType() {
		return EntityType.CUSTOMER;
	}

}
