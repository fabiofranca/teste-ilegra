package com.ilegra.sales.load;

import static com.ilegra.environment.Environment.retrieve;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ilegra.configurations.Configurations;
import com.ilegra.loader.FileLoader;
import com.ilegra.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesFileLoader implements FileLoader{

	private final String INPUT_FOLDER_KEY = "sales.input.folder";
	private Configurations config = retrieve(Configurations.class);

	public List<File> loadAllFiles(){
		log.debug("Loading Sales input files...");
		return loadAllFilesInFolder(config.getString(INPUT_FOLDER_KEY));
	}

	private List<File> loadAllFilesInFolder(String folderName) {
		List<File> inputFiles = null;
		try {
			Path rootFolder = loadFolder(folderName);
			inputFiles = FileUtil.listAllFilesInFolder(rootFolder);
			log.debug("{} files found", inputFiles.size());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			log.error("Error when load sales files: {} - {}", e.getMessage(), e.getCause());
		}
		return inputFiles;
	}

	public Path loadFolder(String folderName) throws URISyntaxException {
		URL folderPath = ClassLoader.getSystemResource(folderName);
		Path rootFolder = Paths.get(folderPath.toURI());
		return rootFolder;
	}
}
