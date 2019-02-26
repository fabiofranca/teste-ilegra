package com.ilegra.configurations;

import com.ilegra.environment.Component;

@Component
public interface Configurations {
	String getString(String key);
	Integer getInteger(String key);
}
