package com.ilegra.loader;

import java.io.File;
import java.util.List;

import com.ilegra.environment.Component;

@Component
public interface FileLoader {
	List<File> loadAllFiles();
}
