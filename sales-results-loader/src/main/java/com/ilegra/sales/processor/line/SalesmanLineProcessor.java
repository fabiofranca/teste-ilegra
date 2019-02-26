package com.ilegra.sales.processor.line;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.ilegra.sales.entity.EntityType;
import com.ilegra.sales.entity.Salesman;
import com.ilegra.util.RegexUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesmanLineProcessor implements LineProcessor<Salesman> {
	
	private static final String REGEX = "(001.)(\\d{13}.)(\\w*.)(\\d+\\.?\\d{2})";
	
	private List<Salesman> sallers;
	
	@Override
	public List<Salesman> processLine(String line) {
		identifySallers(line);
		return sallers;
	}
	
	private void identifySallers(String line) {
		sallers = new ArrayList<>();
		Matcher matcher = RegexUtil.createMatcher(line, REGEX);
		while (matcher.find()) {
		    String registry =  matcher.group(0);
		    log.debug("Salesman found: {}", registry);
			sallers.add(new Salesman(registry, REGISTRY_DELIMITER));
		}
	}

	@Override
	public EntityType geEntityType() {return EntityType.SALESMAN;}
}
