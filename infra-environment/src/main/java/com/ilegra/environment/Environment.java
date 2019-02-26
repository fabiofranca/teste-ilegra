package com.ilegra.environment;

import java.util.HashMap;
import java.util.Map;

import lombok.Synchronized;

public class Environment {
	
	private static final Map<Class<?>, Object> instances = new HashMap<>();

	@Synchronized
	public static void registry(Object component) {
		Class<?> key = findComponentInterface(component.getClass());
		
		if (!key.isInterface())
			throw new EnvironmentException(key.getName() + " is not a Interface!");

		instances.put(key, component);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T retrieve(Class<T> clazz) {
		if (instances.containsKey(clazz))
			return (T) instances.get(clazz);
		throw new EnvironmentException(String.format("No instance found for %s", clazz.getName()));
	}
	
	private static Class<?> findComponentInterface(Class<?> clazz) {
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> candidate : interfaces)
			if (candidate.getAnnotation(Component.class) != null)
				return candidate;

		throw new EnvironmentException(String.format("An interface with annotation @Component was not found (%s)", clazz.getName()));
	}

}
