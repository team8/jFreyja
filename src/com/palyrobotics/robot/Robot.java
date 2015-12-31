/* Created Tue Dec 01 14:35:41 PST 2015 */
package com.palyrobotics.robot;

import org.strongback.Strongback;
import org.strongback.components.ui.FlightStick;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	private Drivetrain drivetrain;
	
	private FlightStick joystick;
	
    @Override
    public void robotInit() {
    	drivetrain = new Drivetrain(joystick);
    }

    @Override
    public void teleopInit() {
        // Start Strongback functions ...
        Strongback.start();
    }

    @Override
    public void teleopPeriodic() {
    	drivetrain.drive();
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

}
