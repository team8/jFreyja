package com.palyrobotics.commands;

import org.strongback.command.Command;
import org.strongback.components.AngleSensor;

import com.palyrobotics.robot.Drivetrain;

public class DriveDist extends Command {
	private final Drivetrain drivetrain;
	private final AngleSensor leftEncoder;
	private final AngleSensor rightEncoder;
	private final double targetDistance;
	
	public DriveDist(Drivetrain drivetrain, AngleSensor leftEncoder, AngleSensor rightEncoder, double targetDistance) {
		this.drivetrain = drivetrain;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.targetDistance = targetDistance;
	}

	@Override
	public boolean execute() {
		
		return false;
	}
}
