package jFreyjaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team8.subsystems.*;

public class LifterTest extends SubsystemTest {

	/**
	 * Tests that init successfully changes the encoder
	 */
	@Override
	public void testInit() {
		Lifter l1 = new Lifter();
		Lifter l2 = new Lifter();
		l1.init();
		assertNotEquals(l2,l1);
	}

	@Override
	public void testUpdate() {
		Lifter l1 = new Lifter();
		Lifter l2 = new Lifter();
		l1.update();
		assertNotEquals(l2,l1);
	}

	@Override
	public void testDisable() {
		Lifter l1 = new Lifter();
		Lifter l2 = new Lifter();
		l1.idle();
		l1.disable();
		assertNotEquals(l2,l1);	
	}

	@Override
	public void testIdle() {
		Lifter l1 = new Lifter();
		Lifter l2 = new Lifter();
		l1.idle();
		assertNotEquals(l2,l1);		
	}

}
