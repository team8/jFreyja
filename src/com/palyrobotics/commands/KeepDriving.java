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
		System.out.println("---------------------------------------------------------------------------");
		double begin = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - begin < 1000) {
			System.out.println("COMMAND RUNNING YAYAYAYAYAYAYAYA");
		}
		return true;
	}

}
