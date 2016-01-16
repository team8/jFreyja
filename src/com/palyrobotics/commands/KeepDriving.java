package com.palyrobotics.commands;

import org.strongback.command.Command;

import com.palyrobotics.robot.Drivetrain;

public class KeepDriving extends Command {

	private Drivetrain drivetrain;
	
	public KeepDriving(Drivetrain drivetrain) {
		this.drivetrain = drivetrain;
	}
	
	@Override
	public boolean execute() {
		
		drivetrain.drive(.3, 0);
		
		return false;
	}

}
