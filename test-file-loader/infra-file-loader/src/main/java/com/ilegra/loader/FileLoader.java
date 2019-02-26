package com.ilegra.loader;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import com.ilegra.environment.Component;

@Component
public interface FileLoader {
	List<File> loadAllFiles();
	Path loadFolder(String folderName) throws URISyntaxException; 
}
