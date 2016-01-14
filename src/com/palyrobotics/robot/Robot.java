/* Created Tue Dec 01 14:35:41 PST 2015 */
package com.palyrobotics.robot;

import org.strongback.Strongback;

import org.strongback.SwitchReactor;
import org.strongback.components.AngleSensor;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.hardware.Hardware;

import com.palyrobotics.robot.Drivetrain;
import com.palyrobotics.commands.DriveDist;

import edu.wpi.first.wpilibj.IterativeRobot;

//import static com.palyrobotics.robot.*;

public class Robot extends IterativeRobot {

	//The drivetrain used
	private Drivetrain drivetrain;
	
	//constants
	public static final int DRIVETRAIN_TALON_LEFT_FRONT = 3;
	public static final int DRIVETRAIN_TALON_LEFT_BACK = 2;
	public static final int DRIVETRAIN_TALON_RIGHT_FRONT = 1;
	public static final int DRIVETRAIN_TALON_RIGHT_BACK = 0;
	
	/** Laptop USB */
	public static final int JOYSTICK_OPERATOR = 0;
	public static final int JOYSTICK_DRIVE = 1;
	public static final int JOYSTICK_TURN = 2;
	
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
	private AngleSensor leftEncoder = Hardware.AngleSensors.encoder(0, 1, 0);
	private AngleSensor rightEncoder = Hardware.AngleSensors.encoder(2, 3, 0);
	
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
        try{
        	Strongback.start();
        }
        catch(Throwable error){
        	System.out.println("rip");
        }
    }

    @Override
    public void teleopPeriodic() {
    	
    	//when operator trigger pressed, call drivedist with distance 10 and tolerance 1
    	//commandCaller.onTriggered(operatorStick.getTrigger(),()->Strongback.submit(new DriveDist(drivetrain, leftEncoder, rightEncoder, 10, 1)));
    	System.out.println("driving dist");
    	//constantly drives the robot according to joystick input
    	drivetrain.drive();
    	System.out.println("driving");
    	
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

}
