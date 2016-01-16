package tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.strongback.mock.Mock;
import org.strongback.mock.MockController;
import org.strongback.mock.MockMotor;
import org.strongback.mock.MockTalonSRX;

import com.palyrobotics.robot.Drivetrain;

public class DrivetrainTest {
	private Drivetrain d;
	private MockMotor m1;
	private MockMotor m2;
	private MockController t1;
	private MockController t2;

	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public TestRule globalTimeout = new Timeout(3000);
	
	@Before
	public void beforeAll() {
		m1 = Mock.stoppedMotor();
		m2 = Mock.stoppedMotor();
		t1 = Mock.controller();
		t2 = Mock.controller();
	}
	
	@Test
	public void testInvalidDTMotors() {
		thrown.expect(NullPointerException.class);
		
		 d = new Drivetrain(null, null, null, null);
	}

	@Test
	public void test() {
	//	d = new Drivetrain(m1,m2, t1, t2);
	}
}
