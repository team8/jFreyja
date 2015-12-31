package com.palyrobotics.robot;

import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;

public class Drivetrain extends TankDrive {
	
	private FlightStick stick;
	
	private Motor left;
	private Motor right;
	
	public Drivetrain(FlightStick stick, Motor left, Motor right) {
		super(left, right);
		this.left = left;
		this.right = right;
		this.stick = stick;
	}
	
	public void drive() {
		this.cheesy(stick.getPitch().read(), stick.getYaw().read(), false);
	}
}
