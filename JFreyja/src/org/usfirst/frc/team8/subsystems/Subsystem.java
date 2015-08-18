package org.usfirst.frc.team8.subsystems;

public abstract class Subsystem {
	/**
	 * Initializes the subystem
	 */
	public abstract void init();
	/**
	 * Updates the subystem, to be called periodically
	 */
	public abstract void update();
	/**
	 * To disable the system when robot is disabled
	 */
	public abstract void disable();
	/**
	 * Makes the subsystem idle
	 */
	public abstract void idle();
	public abstract boolean isIdle();
}
