package jFreyjaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team8.subsystems.*;

public class DrivetrainTest extends SubsystemTest {

	@Override
	public void testInit() {
	//	Drivetrain d1 = new Drivetrain();
	//	d1.init();
	}

	@Override
	public void testUpdate() {
		
	}

	@Override
	public void testDisable() {
		Drivetrain d1 = new Drivetrain();
		Drivetrain d2 = new Drivetrain();
		d1.disable();
		assertNotEquals(d2,d1);
	}

	@Override
	public void testIdle() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testDrive() {
		
	}

	@Test
	public void testDriveDist() {
		
	}
	
	@Test
	public void testRotateAngle() {
		
	}
	
	@Test
	public void testBrake() {
		
	}
}
