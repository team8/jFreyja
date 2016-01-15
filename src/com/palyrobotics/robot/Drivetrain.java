package com.palyrobotics.robot;

import org.strongback.components.AngleSensor;
import org.strongback.components.DistanceSensor;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.control.SoftwarePIDController;
import org.strongback.control.TalonController;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;

import java.util.function.*;

public class Drivetrain extends TankDrive {
	//constants 
	public static final int PID_TOLERANCE = 1;	
	
	//PID Variables
	public static final double PULSES_PER_DEGREE_LEFT = 1;
	public static final double PULSES_PER_DEGREE_RIGHT = 1;
	
	public static final double LEFT_ANALOG_TURNS_OVER_VOLTAGE_RANGE = 0;
	public static final double RIGHT_ANALOG_TURNS_OVER_VOLTAGE_RANGE = 0;
	public static final double MIN_PID_INPUT = -1000;
	public static final double MAX_PID_INPUT = 1000;
	
	//the joystick for input
	private FlightStick turnStick;
	private FlightStick driveStick;
	
	//the motors of this drivetrain
	private Motor left;
	private Motor right;
	
	//PIDController to be activated by commands when needed
//	private SoftwarePIDController leftController;
	
	private TalonController leftController = Hardware.Controllers.talonController(2, PULSES_PER_DEGREE_LEFT, LEFT_ANALOG_TURNS_OVER_VOLTAGE_RANGE);
	private TalonController rightController = Hardware.Controllers.talonController(0, PULSES_PER_DEGREE_RIGHT, RIGHT_ANALOG_TURNS_OVER_VOLTAGE_RANGE);
	
	//Encoders on the drivetrain talons
//	private DistanceSensor leftEncoder;
//	private DistanceSensor rightEncoder;
	
	
	
	public Drivetrain(FlightStick driveStick, FlightStick turnStick, Motor left, Motor right) {
		//calls superclass constructor, creating a tankdrive with 2 motors
		super(left, right);
		this.left = left;
		this.right = right;
		this.turnStick = turnStick;
		this.driveStick = driveStick;
		
		//this.leftController = new SoftwarePIDController(() -> leftEncoder.getAngle(), (leftEncoder.getAngle()) -> left.setSpeed());
		//this.leftController.withInputRange(MIN_PID_INPUT, MAX_PID_INPUT).withTolerance(PID_TOLERANCE);
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
	public void drive() {
		this.cheesy(turnStick.getRoll().read(), -driveStick.getPitch().read(), true);
		
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
	
	public FlightStick getTurnStick() {
		return turnStick;
	}
	
	public FlightStick getDriveStick() {
		return driveStick;
	}

}
