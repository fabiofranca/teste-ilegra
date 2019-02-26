package com.ilegra.sales.load;

import static com.ilegra.environment.Environment.registry;
import static com.ilegra.environment.Environment.retrieve;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ilegra.configurations.CommonConfigurationsImpl;
import com.ilegra.loader.FileLoader;

public class SalesFileLoaderTest {

	private static final int NUMBER_OF_FILES_TO_PROCESS = 3;
	private FileLoader salesLoader;

	public SalesFileLoaderTest() {
		registry(new CommonConfigurationsImpl());
		registry(new SalesFileLoader());
	}

	@Before
	public void load() {
		salesLoader = retrieve(FileLoader.class);
	}

	@Test
	public void loadAllSalesInputFiles() {
		assertEquals(NUMBER_OF_FILES_TO_PROCESS, salesLoader.loadAllFiles().size());
	}

}
