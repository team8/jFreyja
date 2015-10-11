package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.subsystems.LifterHelper;
import org.usfirst.frc.team8.subsystems.LifterHelper.State;


//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.PIDSource;
import stubs.*;

/**
 * Controls the lifter subsystem with PID or joysticks
 * Uses a helper class for constants and helper methods
 * @see LifterHelper
 * @author Nihar
 *
 */
public class Lifter extends Subsystem {
	/** Stores the state of the lifter */
	public State state = State.IDLE;

	/** Victors to drive the lifter */
	public Victor victor1 = new Victor();
	public Victor victor2 = new Victor();
	
	/** PID Components */
	protected Encoder encoder = new Encoder();
	protected PIDController controller1 = new PIDController();
	protected PIDController controller2 = new PIDController();
	
	/** Hall effect sensors for the elevator */
	protected DigitalInput topSensor = new DigitalInput();
	protected DigitalInput bottomSensor = new DigitalInput();
	
	/** Stores the current level */
	double currentLevel;
	
	@Override
	public void init() {
		encoder.reset();
	}

	@Override
	public void update() {
		currentLevel = encoder.getDistance() / LifterHelper.LEVEL_HEIGHT;
		
		switch(state) {
		case IDLE:
			break;
		case TELEOP:
			break;
		case AUTOMATED:
			if(encoder.getStopped() && 
					controller1.getError() < LifterHelper.ACCEPTABLE_PID_ERROR &&
					controller2.getError() < LifterHelper.ACCEPTABLE_PID_ERROR) {
				idle();
			}
			break;
		case DISABLED:
			break;
		}
		
		if(isBottomHit()) {
			setVelocity(LifterHelper.BOUNCE_SPEED);
		}
		else if(isTopHit()) {
			setVelocity(-LifterHelper.BOUNCE_SPEED);
		}
	}

	@Override
	public void disable() {
		controller1.disable();
		controller2.disable();
		setState(State.DISABLED);
	}

	@Override
	public void idle() {
		encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
		controller1.setSetpoint(0);
		controller2.setSetpoint(0);
		controller1.enable();
		controller2.enable();
		setState(State.IDLE);
	}

	@Override
	public boolean isIdle() {
		return (state == State.IDLE);
	}
	
	private void setVelocity(double velocity) {
		double computedVelocity = Math.min(
				Math.max(velocity * LifterHelper.SPEED_SCALING, -LifterHelper.MAX_SPEED),
				LifterHelper.MAX_SPEED);
		victor1.set(-computedVelocity);
		victor2.set(-computedVelocity);
		setState(State.TELEOP);
	}
	
	private void setState(State state) {
		if(state == State.DISABLED) {
			return;
		}
		this.state = state;
	}
	
	private boolean isBottomHit() {
		return bottomSensor.get();
	}
	
	private boolean isTopHit() {
		return topSensor.get();
	}
}
