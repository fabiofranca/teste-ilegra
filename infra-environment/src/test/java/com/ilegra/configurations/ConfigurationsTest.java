package com.ilegra.configurations;

import static com.ilegra.environment.Environment.registry;
import static com.ilegra.environment.Environment.retrieve;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ConfigurationsTest {
	
	private static final String FIRST_CONFIG_KEY = "config.first";
	private static final String ONE_CONFIG_KEY = "config.one";
	private Configurations conf ;
	
	public ConfigurationsTest() {
		registry(new CommonConfigurationsImpl());
		conf = retrieve(Configurations.class);
	}

	@Test
	public void shouldLoadStringConfig() {
		String firstConfig = conf.getString(FIRST_CONFIG_KEY);
		assertNotNull(firstConfig);
		assertEquals("first", firstConfig);
	}
	
	@Test
	public void shouldLoadIntegerConfig() {
		Integer oneConfig = conf.getInteger(ONE_CONFIG_KEY);
		assertNotNull(oneConfig);
		assertEquals(new Integer(1), oneConfig);
	}
}
