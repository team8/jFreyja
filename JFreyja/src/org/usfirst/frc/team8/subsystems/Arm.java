package org.usfirst.frc.team8.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Arm extends Subsystem{
	
	public enum State {
		OPEN,
		CLOSED,
		OPENING,
		CLOSING
	}
	
	State state;
	
	Compressor compressor;
	
	Solenoid solenoid;
	@Override
	public void init() {
		state = State.OPEN;
		compressor.start();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void idle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isIdle() {
		// TODO Auto-generated method stub
		return false;
	}

}
