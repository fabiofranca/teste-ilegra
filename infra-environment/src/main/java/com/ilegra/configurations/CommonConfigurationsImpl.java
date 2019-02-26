package com.ilegra.configurations;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonConfigurationsImpl implements Configurations{

	private static final String CONFIG_FILE_NAME = "project.properties";
	private Configuration config;
	
	public CommonConfigurationsImpl() {
		try {
			log.info("Loading {} file...", CONFIG_FILE_NAME);
			config = new PropertiesConfiguration(CONFIG_FILE_NAME);
			config.getKeys().forEachRemaining(k -> log.info("Config Key {} = {}", k, config.getString(k+"")));
		} catch (ConfigurationException e) {
			log.error("When trying to load config file: {} - {}", e.getCause(), e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public String getString(String key) {
		return config.getString(key);
	}
	
	public Integer getInteger(String key) {
		return config.getInt(key);
	}
}
