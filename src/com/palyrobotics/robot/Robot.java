/* Created Tue Dec 01 14:35:41 PST 2015 */
package com.palyrobotics.robot;

import static com.palyrobotics.robot.Constants.Ports.*;
import org.strongback.Strongback;
import org.strongback.SwitchReactor;
import org.strongback.components.AngleSensor;
import org.strongback.components.Motor;
import org.strongback.components.ui.FlightStick;
import org.strongback.hardware.Hardware;

import com.palyrobotics.commands.DriveDist;
import com.palyrobotics.commands.KeepDriving;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	//The drivetrain used
	private Drivetrain drivetrain;
	
	//The joystick used
	private FlightStick operatorStick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_OPERATOR);
	private FlightStick driveStick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_DRIVE);
	private FlightStick turnStick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_TURN);
	
	//The motors, these are used in the drivetrain
	private Motor leftFrontMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_LEFT_FRONT);
	private Motor leftBackMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_LEFT_BACK);
	private Motor leftMidMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_LEFT_MID);
	
	private Motor rightFrontMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_RIGHT_FRONT);
	private Motor rightBackMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_RIGHT_BACK);
	private Motor rightMidMotor = Hardware.Motors.talon(DRIVETRAIN_TALON_RIGHT_MID);
	
	//merging the motors on each side
	private Motor left = Motor.compose(leftBackMotor, leftFrontMotor, leftMidMotor);
	private Motor right = Motor.compose(rightBackMotor, rightFrontMotor, rightMidMotor);
	
	//encoders. numbers are 1st port, 2nd port, dpp
	private AngleSensor leftEncoder = Hardware.AngleSensors.encoder(0, 1, .1);
	private AngleSensor rightEncoder = Hardware.AngleSensors.encoder(2, 3, .1);

	private SwitchReactor commandCaller;
	
    @Override
    public void robotInit() {
    	//passes the motors to be used to the drivetrain, along with a joystick
    	drivetrain = new Drivetrain(driveStick, turnStick, left, right);
    	
    	//creates the syste that calls commands on button press
    	commandCaller = Strongback.switchReactor();
    }

    @Override
    public void teleopInit() {
        // Start Strongback functions ...
        try{
        	Strongback.start();
        	//Strongback.submit(new DriveDist(drivetrain, leftEncoder, rightEncoder, 1000, 1));
        	//Strongback.submit(new KeepDriving(drivetrain));
        }
        catch(Throwable error){
        	System.err.println("rip" + error);
        }
    }

    @Override
    public void teleopPeriodic() {
    	//System.out.println("encoder output: " + leftEncoder.getAngle());
    	
    	//when operator trigger pressed, call drivedist with distance 10 and tolerance 1
    	//commandCaller.onTriggered(operatorStick.getButton(8),()->Strongback.submit(new KeepDriving(drivetrain)));
    	//Strongback.submit(new KeepDriving(drivetrain));
    	//constantly drives the robot according to joystick input
    	drivetrain.drive(-driveStick.getPitch().read(), turnStick.getRoll().read());  	
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }
    
    @Override
    public void disabledPeriodic() {
    	
    }
    
    /**
     * Prints out joystick buttons when pressed
     */
    private void joystickTest() {
    	commandCaller.onTriggered(operatorStick.getButton(1), () -> System.out.println("Operator 1"));
    	commandCaller.onTriggered(operatorStick.getButton(2), () -> System.out.println("Operator 2"));
    	commandCaller.onTriggered(operatorStick.getButton(3), () -> System.out.println("Operator 3"));
    	commandCaller.onTriggered(operatorStick.getButton(4), () -> System.out.println("Operator 4"));
    	commandCaller.onTriggered(operatorStick.getButton(5), () -> System.out.println("Operator 5"));
    	commandCaller.onTriggered(operatorStick.getButton(6), () -> System.out.println("Operator 6"));
    	commandCaller.onTriggered(operatorStick.getButton(7), () -> System.out.println("Operator 7"));
    	commandCaller.onTriggered(operatorStick.getButton(8), () -> System.out.println("Operator 8"));
    	commandCaller.onTriggered(operatorStick.getButton(9), () -> System.out.println("Operator 9"));
    	commandCaller.onTriggered(operatorStick.getButton(10), () -> System.out.println("Operator 10"));
    	commandCaller.onTriggered(operatorStick.getButton(11), () -> System.out.println("Operator 11"));
    	commandCaller.onTriggered(operatorStick.getButton(12), () -> System.out.println("Operator 12"));
    	
    }
}
