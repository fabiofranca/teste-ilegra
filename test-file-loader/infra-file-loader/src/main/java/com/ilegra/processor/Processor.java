package com.ilegra.processor;

import com.ilegra.environment.Component;

@Component
public interface Processor {
	
	void process() throws Exception;
}
