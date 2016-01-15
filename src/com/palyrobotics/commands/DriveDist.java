package com.palyrobotics.commands;

import org.strongback.command.Command;
import org.strongback.components.AngleSensor;
import org.strongback.components.DistanceSensor;
import org.strongback.control.SoftwarePIDController;
import org.strongback.hardware.Hardware;

import com.palyrobotics.robot.Drivetrain;

public class DriveDist extends Command {
	private final Drivetrain drivetrain;
	private final AngleSensor leftEncoder;
	private final AngleSensor rightEncoder;
	private final double targetDistance;
	private SoftwarePIDController controller;
	
	public DriveDist(Drivetrain drivetrain, AngleSensor leftEncoder, AngleSensor rightEncoder, double targetDistance, double tolerance) {
		super(drivetrain);
		this.drivetrain = drivetrain;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.targetDistance = targetDistance;
		
		//port, pulses per degree, an
	}

	@Override
	public boolean execute() {
		drivetrain.driveDist(targetDistance);
		return drivetrain.hasArrived();
	}
}
