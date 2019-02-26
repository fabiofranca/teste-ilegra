package com.ilegra.sales.processor;

import static com.ilegra.environment.Environment.retrieve;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.LineIterator;

import com.ilegra.loader.FileLoader;
import com.ilegra.processor.Processor;
import com.ilegra.sales.entity.Entity;
import com.ilegra.sales.entity.EntityType;
import com.ilegra.sales.load.SalesFileLoader;
import com.ilegra.sales.processor.line.LineProcessor;
import com.ilegra.util.FileUtil;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesFileProcessor implements Processor {
	
	private List<File> filesToProcess;
	private SalesFileLoader loader;

	@Getter 
	private List<LineProcessor<Entity>> lineProcessors;
	
	@Getter 
	private HashMap<EntityType, List<Entity>> registries;

	public SalesFileProcessor() {
		this.lineProcessors = new ArrayList<>();
		this.loader = (SalesFileLoader) retrieve(FileLoader.class);
		this.registries = new HashMap<>();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void regitryProcessor(LineProcessor lineProcessor) {
		this.lineProcessors.add(lineProcessor);
	}
	
	@Override
	public void process() throws Exception {
		if(lineProcessors.size() == 0) throw new Exception("No registered processors, please use regitryProcessor()");
		filesToProcess = loader.loadAllFiles();
		
		for (File file : filesToProcess) {
			processFile(file);
			FileUtil.deleteQuietly(file);
		}
	}

	private void processFile(File file) {
		log.debug("processing file {}", file.getName());
		try {
			LineIterator lines = FileUtil.lineIterator(file);
			while(hasLines(lines)) identifyEntitiesInLine(lines.nextLine());
			
		} catch (IOException e) {
			log.error("Error when try to read line: {} - {}", e.getMessage(), e.getCause());
		}
	}

	private void identifyEntitiesInLine(String line) {
		for (LineProcessor<Entity> lineProcessor : lineProcessors) {
			EntityType type = lineProcessor.geEntityType();
			List<Entity> entities = retrieveOrCreateEntityList(type);
			entities.addAll(lineProcessor.processLine(line));
			registries.put(type, entities);
		}
	}

	private List<Entity> retrieveOrCreateEntityList(EntityType type) {
		return entityAlreadyProcessed(type) ? getListEntities(type) : new ArrayList<>();
	}

	private boolean hasLines(LineIterator lines) {
		return lines.hasNext();
	}

	private boolean entityAlreadyProcessed(EntityType type) {
		return getListEntities(type) != null;
	}
	
	private List<Entity> getListEntities(EntityType type) {
		return (List<Entity>) registries.get(type);
	}

}
