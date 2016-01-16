package com.palyrobotics.commands;

import org.strongback.command.Command;

import com.palyrobotics.robot.Drivetrain;

public class DriveBack extends Command{

	Drivetrain drivetrain;
	
	public DriveBack(Drivetrain drivetrain) {
		this.drivetrain = drivetrain;
	}
	
	@Override
	public boolean execute() {
		System.out.println("------DRIVING BACK--------");
		double begin = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - begin < 1000) {
			drivetrain.drive(.1, 0);
		}
		return true;
	}

}
