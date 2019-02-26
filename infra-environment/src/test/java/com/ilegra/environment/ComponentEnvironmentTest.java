package com.ilegra.environment;

import static com.ilegra.environment.Environment.registry;
import static com.ilegra.environment.Environment.retrieve;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ComponentEnvironmentTest {
	
	@Test
	public void shouldInitializeComponents() {
		registry(new ComponentInterfaceImpl());
		ComponentInterface c1 = retrieve(ComponentInterface.class);
		assertNotNull(c1);
		assertTrue(c1 instanceof ComponentInterfaceImpl);
		
		ComponentInterface c2 = retrieve(ComponentInterface.class);
		assertSame(c1, c2);
		
		registry(new ComponentInterfaceImpl2());
		ComponentInterface c3 = retrieve(ComponentInterface.class);
		assertNotNull(c3);
		assertNotSame(c1, c3);
		assertTrue(c3 instanceof ComponentInterface);
	}
	
	@Test
	public void shouldNotRegisterAnInstance() {
		try {
			registry(new NotComponentImpl());
			fail();
		} catch (EnvironmentException e) {}	
		
		try {
			retrieve(NotComponent.class);
			fail();
		} catch (EnvironmentException e) {}
	}
	
	

}
@Component
interface ComponentInterface{}
interface NotComponent{}

class NotComponentImpl implements NotComponent{}
class ComponentInterfaceImpl implements ComponentInterface{}
class ComponentInterfaceImpl2 implements ComponentInterface{}
