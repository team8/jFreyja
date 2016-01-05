package com.palyrobotics.robot;

import org.strongback.components.AngleSensor;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.control.SoftwarePIDController;
import org.strongback.drive.TankDrive;
import java.util.function.*;
import static com.palyrobotics.robot.Constants.Drivetrain.*;

public class Drivetrain extends TankDrive {
	//the joystick for input
	private FlightStick turnStick;
	private FlightStick driveStick;
	
	//the motors of this drivetrain
	private Motor left;
	private Motor right;
	
	//PIDController to be activated by commands when needed
	private SoftwarePIDController controller;
	
	//Encoders on the drivetrain talons
	private AngleSensor leftEncoder;
	private AngleSensor rightEncoder;
	
	public Drivetrain(FlightStick driveStick, FlightStick turnStick, Motor left, Motor right) {
		//calls superclass constructor, creating a tankdrive with 2 motors
		super(left, right);
		this.left = left;
		this.right = right;
		this.turnStick = turnStick;
		this.driveStick = driveStick;
		
//		this.controller = new SoftwarePIDController(new DoubleSupplier(leftEncoder, rightEncoder), );
		this.controller.withInputRange(-500, 500).withTolerance(PID_TOLERANCE);
	}
	
	//uses cheesy drive(1 joystick forward/backward, 1 joystick turning) to drive
	public void drive() {
		this.cheesy(driveStick.getPitch().read(), turnStick.getYaw().read(), false);
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
