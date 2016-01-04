package com.palyrobotics.commands;

import org.strongback.command.Command;
import org.strongback.control.Controller;
import org.strongback.control.PIDController;
import org.strongback.control.SoftwarePIDController;
import org.strongback.hardware.Hardware;
import org.strongback.components.AngleSensor;
import org.strongback.components.Motor;

import com.palyrobotics.robot.Drivetrain;

public class DriveDist extends Command {
	private final Drivetrain drivetrain;
	private final AngleSensor leftEncoder;
	private final AngleSensor rightEncoder;
	private final double targetDistance;
	private final Motor left;
	private PIDController controller;
	
	public DriveDist(Drivetrain drivetrain, AngleSensor leftEncoder, AngleSensor rightEncoder, double targetDistance, double tolerance) {
		super(drivetrain);
		this.drivetrain = drivetrain;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.targetDistance = targetDistance;
		
		//port, pulses per degree, an
		controller = HardwareTalonController.
	}

	@Override
	public boolean execute() {
		controller.updateSetpoint();
		return false;
	}
}
