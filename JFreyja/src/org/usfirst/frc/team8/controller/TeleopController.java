package org.usfirst.frc.team8.controller;

import org.usfirst.frc.team8.robot.Robot;
import org.usfirst.frc.team8.subsystems.Arm;
import org.usfirst.frc.team8.subsystems.Drivetrain;
import org.usfirst.frc.team8.subsystems.Lifter;
import org.usfirst.frc.team8.subsystems.Ramp;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopController {
	
	private Drivetrain drivetrain;
	private Lifter lifter;
	
	private Joystick driveStick;
	private Joystick turnStick;
	private Joystick operatorStick;
	
	public TeleopController(Robot robot) {
		driveStick = new Joystick(0); //numbers are port numbers
		turnStick = new Joystick(1);
		operatorStick = new Joystick(2);
		
		drivetrain = robot.getDrivetrain();
		lifter = robot.getLifter();
	}
	
	public void update() {
		//Drivetrain joystick controls
		drivetrain.drive(turnStick.getX(), driveStick.getY());
		
		//Lifter joystick controls
		lifter.setVelocity(operatorStick.getY());
		
		if(driveStick.getTrigger()) {
			drivetrain.driveDist(10);
		}
		
		//Button controls	
		if(drivetrain.driveControllerError() < 1) {
			drivetrain.idle();
		}
	}
}
