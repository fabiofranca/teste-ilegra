package com.ilegra.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class FileUtil extends FileUtils{
	
	public static List<File> listAllFilesInFolder(Path rootFolder) throws IOException {
		Path path = rootFolder.toAbsolutePath();
		try{
			return Files.walk(path)
					.filter(Files :: isRegularFile)
					.map(Path :: toFile)
					.collect(Collectors.toList());
		}catch(NoSuchFileException noFile){
			return new ArrayList<File>();
		}
	}
}
