package com.palyrobotics.commands;

import org.strongback.command.CommandGroup;

import com.palyrobotics.robot.Drivetrain;

public class ForwardThenBack extends CommandGroup {
	public ForwardThenBack(Drivetrain drivetrain) {
		sequentially(new KeepDriving(drivetrain), new DriveBack(drivetrain));
	}
}
