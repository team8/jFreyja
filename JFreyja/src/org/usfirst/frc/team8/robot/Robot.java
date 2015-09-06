
package org.usfirst.frc.team8.robot;

import org.usfirst.frc.team8.controller.*;
import org.usfirst.frc.team8.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private TeleopController teleController;
	private AutonomousController autoController;
	
	private Arm arm;
	private Drivetrain drivetrain;
	private Lifter lifter;
	private Ramp ramp;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	arm = new Arm();
    	drivetrain = new Drivetrain();
    	lifter = new Lifter();
    	ramp = new Ramp();
    	
    	teleController = new TeleopController(this);
    	autoController = new AutonomousController(this);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        teleController.update();
        updateSubsystems();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }    
    
    public void 
    
    public void updateSubsystems() {
    	//arm.update();
    	drivetrain.update();
    	lifter.update();
    	//ramp.update();
    }
    
    public Arm getArm() {
    	return arm;
    }
    
    public Drivetrain getDrivetrain() {
    	return drivetrain;
    }
    
    public Lifter getLifter() {
    	return lifter;
    }
    
    public Ramp getRamp() {
    	return ramp;
    }
}