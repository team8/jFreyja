package com.palyrobotics.robot;

import org.strongback.components.Motor;
import org.strongback.components.ThreeAxisAccelerometer;
import org.strongback.components.ui.FlightStick;
import org.strongback.control.TalonController;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;

public class Drivetrain extends TankDrive {
	//constants 
	public static final int PID_TOLERANCE = 1;	

	public static final double MIN_PID_INPUT = -1000;
	public static final double MAX_PID_INPUT = 1000;

	//the motors of this drivetrain
	private Motor left;
	private Motor right;

	private TalonController leftController;
	private TalonController rightController;
	
//	private ThreeAxisAccelerometer test = Hardware.Accelerometers.builtIn();
	
	public Drivetrain(Motor left, Motor right, TalonController leftcontroller, TalonController rightcontroller) {
		//calls superclass constructor, creating a tankdrive with 2 motors
		super(left, right);
		this.left = left;
		this.right = right;

		this.leftController = leftController;
		this.rightController = rightController;
		
		leftController.withGains(.1, 0, .1);
		rightController.withGains(.1, 0, .1);
	}
	
	public boolean hasArrived() {
		return leftController.isWithinTolerance() && rightController.isWithinTolerance();
	}
	
	public void driveDist(double dist) {
		//in angles, will convert to distances later
		leftController.withTarget(dist);
		rightController.withTarget(dist);
	}
	
	//uses cheesy drive(1 joystick forward/backward, 1 joystick turning) to drive
	public void drive(double forward, double turn) {
		this.cheesy(turn, forward, true);
//		System.out.println("x: " + test.getXDirection().getAcceleration());
//		System.out.println("y: " + test.getYDirection().getAcceleration());
//		System.out.println("z: " + test.getZDirection().getAcceleration());
		
//		System.out.println("Drivestick pitch: " + driveStick.getPitch().read());
//		System.out.println("Drivestick yaw: " + driveStick.getYaw().read());
//		System.out.println("Drivestick roll: " + driveStick.getRoll().read());
//		
//		System.out.println("turnstick pitch: " + turnStick.getPitch().read());
//		System.out.println("turnstick yaw: " + turnStick.getYaw().read());
//		System.out.println("turnstick roll: " + turnStick.getRoll().read());
	}
	
	public Motor getLeftMotor() {
		return left;
	}
	
	public Motor getRightMotor() {
		return right;
	}

}
