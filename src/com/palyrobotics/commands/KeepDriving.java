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
		System.out.println("-------------DRIVING FORWARD------------------------");
		double begin = System.currentTimeMillis();
		drivetrain.drive(0.1, 0);
		return true;
	}

}
