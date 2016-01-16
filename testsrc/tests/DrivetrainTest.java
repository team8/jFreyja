package tests;

import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.strongback.components.*;
import org.strongback.components.ui.FlightStick;

import com.palyrobotics.robot.Drivetrain;

public class DrivetrainTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void invalidDTMotors() {
		FlightStick f1 = null;
		FlightStick f2 = null;
		Motor m1 = null;
		Motor m2 = null;
		thrown.expect(UnsatisfiedLinkError.class);
		
		Drivetrain d = new Drivetrain(m1,m2);
	}

	@Test
	public void testHasArrived() {
		fail("Not yet implemented");
	}

	@Test
	public void testDriveDist() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrive() {
		fail("Not yet implemented");
	}
}
