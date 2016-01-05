/* Created Tue Dec 01 14:35:41 PST 2015 */
package com.palyrobotics.robot;

import org.strongback.Strongback;
import org.strongback.SwitchReactor;
import org.strongback.components.AngleSensor;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.hardware.Hardware;

import com.palyrobotics.commands.DriveDist;

import edu.wpi.first.wpilibj.IterativeRobot;

import static com.palyrobotics.robot.Constants.Ports.*;

public class Robot extends IterativeRobot {

	//The drivetrain used
	private Drivetrain drivetrain;
	
	//The joystick used
	private FlightStick operatorStick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_OPERATOR);
	private FlightStick driveStick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_DRIVE);
	private FlightStick turnstick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_TURN);
	
	//The motors, these are used in the drivetrain
	private Motor leftFrontMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_LEFT_FRONT);
	private Motor leftBackMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_LEFT_BACK);
	private Motor rightFrontMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_RIGHT_FRONT);
	private Motor rightBackMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_RIGHT_BACK);
	
	//merging the motors on each side
	private Motor left = Motor.compose(leftBackMotor, leftFrontMotor);
	private Motor right = Motor.compose(rightBackMotor, rightFrontMotor);
	
	//encoders. numbers are 1st port, 2nd port, dpp
	private AngleSensor leftEncoder = Hardware.AngleSensors.encoder(0, 0, 0);
	private AngleSensor rightEncoder = Hardware.AngleSensors.encoder(0, 0, 0);
	
	private SwitchReactor commandCaller;
	
    @Override
    public void robotInit() {
    	//passes the motors to be used to the drivetrain, along with a joystick
    	drivetrain = new Drivetrain(turnstick, driveStick, left, right);
    	
    	//creates the syste that calls commands on button press
    	commandCaller = Strongback.switchReactor();
    }

    @Override
    public void teleopInit() {
        // Start Strongback functions ...
        Strongback.start();
    }

    @Override
    public void teleopPeriodic() {
    	
    	//when operator trigger pressed, call drivedist with distance 10 and tolerance 1
    	commandCaller.onTriggered(operatorStick.getTrigger(),()->Strongback.submit(new DriveDist(drivetrain, leftEncoder, rightEncoder, 10, 1)));
    	
    	//constantly drives the robot according to joystick input
    	drivetrain.drive();
    	
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

}
