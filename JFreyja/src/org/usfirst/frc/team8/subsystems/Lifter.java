package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.robot.Ports;
import org.usfirst.frc.team8.subsystems.LifterHelper;
import org.usfirst.frc.team8.subsystems.LifterHelper.State;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

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
	public Victor victor1 = new Victor(Ports.PORT_LIFTER_VICTOR_1); //numbers are for ports
	public Victor victor2 = new Victor(Ports.PORT_LIFTER_VICTOR_2);
	
	/** PID Components */
	protected Encoder encoder = new Encoder(0, 1, false); //numbers are for ports
	
	protected PIDController controller1 = new PIDController(LifterHelper.PROPORTIONAL_CONSTANT, 
															LifterHelper.INTEGRAL_CONSTANT, 
															LifterHelper.DERIVATIVE_CONSTANT,
															encoder, victor1);
	
	protected PIDController controller2 = new PIDController(LifterHelper.PROPORTIONAL_CONSTANT, 
															LifterHelper.INTEGRAL_CONSTANT, 
															LifterHelper.DERIVATIVE_CONSTANT,
															encoder, victor2);
	
	
	/** Hall effect sensors for the elevator */
	protected DigitalInput topSensor = new DigitalInput(0); //numbers are for ports
	protected DigitalInput bottomSensor = new DigitalInput(1);
	
	/** Stores the current level */
	double currentLevel;
	
	@Override
	public void init() {
		encoder.reset();
		resetZero();
	}

	@Override
	public void update() {
		debug();
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
			//setVelocity(LifterHelper.BOUNCE_SPEED);
		}
		else if(isTopHit()) {
			//setVelocity(-LifterHelper.BOUNCE_SPEED);
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
		encoder.setPIDSourceParameter(PIDSourceParameter.kRate);
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
	
	public void setVelocity(double velocity) {
		double computedVelocity = Math.min(
				Math.max(velocity * LifterHelper.SPEED_SCALING, -LifterHelper.MAX_SPEED),
				LifterHelper.MAX_SPEED);
		victor1.set(-computedVelocity);
		victor2.set(-computedVelocity);
		setState(State.TELEOP);
	}
	
	private void setLevel(double level) {
		double setpoint = level * LifterHelper.LEVEL_HEIGHT;
		encoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		controller1.setSetpoint(setpoint);
		controller1.setSetpoint(setpoint);
		controller2.enable();
		controller2.enable();
		setState(State.AUTOMATED);
	}
	
	public void liftLevel(double liftAmount) {
		double newLevel = currentLevel + liftAmount;
		setLevel(newLevel);
	}
	
	public void zero() {
		setLevel(0);
	}
	
	public void resetZero() {
		encoder.reset();
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
	
	private void debug() {
		System.out.println("Lifter Debug");
	}
}
