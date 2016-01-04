/* Created Tue Dec 01 14:35:41 PST 2015 */
package com.palyrobotics.robot;

import org.strongback.Strongback;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.hardware.Hardware;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	//The drivetrain used
	private Drivetrain drivetrain;
	
	//The joystick used
	private FlightStick turnstick = Hardware.HumanInterfaceDevices.logitechAttack3D(0);
	private FlightStick driveStick = Hardware.HumanInterfaceDevices.logitechAttack3D(0);
	
	//The motors, these are used in the drivetrain
	private Motor leftBackMotor = Hardware.Motors.talon(0);
	private Motor leftFrontMotor = Hardware.Motors.talon(0);
	private Motor rightBackMotor = Hardware.Motors.talon(0);
	private Motor rightFrontMotor = Hardware.Motors.talon(0);
	
	//merging the motors on each side
	private Motor left = Motor.compose(leftBackMotor, leftFrontMotor);
	private Motor right = Motor.compose(rightBackMotor, rightFrontMotor);
	
    @Override
    public void robotInit() {
    	//passes the motors to be used to the drivetrain, along with a joystick
    	drivetrain = new Drivetrain(turnstick, driveStick, left, right);
    }

    @Override
    public void teleopInit() {
        // Start Strongback functions ...
        Strongback.start();
    }

    @Override
    public void teleopPeriodic() {
    	//constantly drives the robot according to joystick input
    	drivetrain.drive();
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

}
