package jFreyjaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team8.subsystems.*;

public class LifterTest extends SubsystemTest {

	@Override
	public void testInit() {

	}

	@Override
	public void testUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testDisable() {
	}

	@Override
	public void testIdle() {
		Lifter l1 = new Lifter();
		Lifter l2 = new Lifter();
		l1.idle();
		assertNotEquals(l2,l1);		
	}

}
