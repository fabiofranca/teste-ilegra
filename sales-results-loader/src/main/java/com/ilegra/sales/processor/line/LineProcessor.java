package com.ilegra.sales.processor.line;

import static com.ilegra.environment.Environment.retrieve;

import java.util.List;

import com.ilegra.configurations.Configurations;
import com.ilegra.sales.entity.Entity;
import com.ilegra.sales.entity.EntityType;

public interface LineProcessor<T extends Entity>{
	
	static final String REGISTRY_DELIMITER = retrieve(Configurations.class).getString("sales.file.registry.delimiter");
	
	List<T> processLine(String line);
	
	EntityType geEntityType();
}
