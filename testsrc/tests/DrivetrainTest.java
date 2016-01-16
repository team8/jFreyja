package tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.mock.Mock;
import org.strongback.mock.MockMotor;

import com.palyrobotics.robot.Drivetrain;

public class DrivetrainTest {
	private Drivetrain d;
	private MockMotor m1;
	private MockMotor m2;
	private FlightStick f1;
	private FlightStick f2;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public TestRule globalTimeout = new Timeout(300);
	
	@Before
	public void beforeAll() {
		m1 = Mock.stoppedMotor();
		m2 = Mock.stoppedMotor();
	}
	
	@Test
	public void testInvalidDTMotors() {
		FlightStick f1 = null;
		FlightStick f2 = null;
		Motor m1 = null;
		Motor m2 = null;
		thrown.expect(UnsatisfiedLinkError.class);
		
		d = new Drivetrain(f1,f2,m1,m2);
	}

	@Test
	public void test() {
		d = new Drivetrain(f1,f2,m1,m2);
	}
}
