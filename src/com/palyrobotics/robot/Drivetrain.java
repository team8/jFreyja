package com.palyrobotics.robot;

import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;

public class Drivetrain extends TankDrive {
	
	//the joystick for input
	private FlightStick stick;
	
	//the motors of this drivetrain
	private Motor left;
	private Motor right;
	
	public Drivetrain(FlightStick stick, Motor left, Motor right) {
		//calls superclass constructor, creating a tankdrive with 2 motors
		super(left, right);
		this.left = left;
		this.right = right;
		this.stick = stick;
	}
	
	//uses cheesy drive(1 joystick forward/backward, 1 joystick turning) to drive
	public void drive() {
		this.cheesy(stick.getPitch().read(), stick.getYaw().read(), false);
	}
	
	public Motor getLeftMotor() {
		return left;
	}
	
	public Motor getRightMotor() {
		return right;
	}
	
	public FlightStick getJoystick() {
		return stick;
	}
}
