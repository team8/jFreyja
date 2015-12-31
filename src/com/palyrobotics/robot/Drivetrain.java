package com.palyrobotics.robot;

import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;

public class Drivetrain extends TankDrive {
	private FlightStick stick;
	
	private static Motor leftBackMotor = Hardware.Motors.talon(0);
	private static Motor leftFrontMotor = Hardware.Motors.talon(0);
	private static Motor rightBackMotor = Hardware.Motors.talon(0);
	private static Motor rightFrontMotor = Hardware.Motors.talon(0);
	
	private static Motor left = Motor.compose(leftBackMotor, leftFrontMotor);
	private static Motor right = Motor.compose(rightBackMotor, rightFrontMotor);
	
	public Drivetrain(FlightStick stick) {
		super(left, right);
		this.stick = stick;
	}
	
	public void drive() {
		this.cheesy(stick.getPitch().read(), stick.getYaw().read(), false);
	}
}
