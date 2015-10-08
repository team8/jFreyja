package jFreyjaTest;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class SubsystemTest {

	@Test
	public abstract void testInit();

	@Test
	public abstract void testUpdate();
	
	@Test
	public abstract void testDisable();
	
	@Test
	public abstract void testIdle();
}
