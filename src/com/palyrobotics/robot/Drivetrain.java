package com.palyrobotics.robot;

import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;

public class Drivetrain extends TankDrive {
	
	//the joystick for input
	private FlightStick turnStick;
	private FlightStick driveStick;
	
	//the motors of this drivetrain
	private Motor left;
	private Motor right;
	
	public Drivetrain(FlightStick driveStick, FlightStick turnStick, Motor left, Motor right) {
		//calls superclass constructor, creating a tankdrive with 2 motors
		super(left, right);
		this.left = left;
		this.right = right;
		this.turnStick = turnStick;
		this.driveStick = driveStick;
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
