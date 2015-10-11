package org.usfirst.frc.team8.subsystems;

/**
 * Helper class for the Lifter subsystem
 * @author Nihar
 * @see Lifter
 */
public class LifterHelper {
	/**
	 * States that lifter can be in
	 *
	 * IDLE - Runs velocity PID to maintain position
	 * TELEOP - Scales joystick input to run talon
	 * AUTOMATED - Runs positon PID to change level
	 * DISABLED - Unoperational for testing other things
	 */
	public enum State {
		IDLE,
		TELEOP,
		AUTOMATED,
		DISABLED
	};
	
	/** PID Constants **/
	public static final double PROPORTIONAL_CONSTANT = 1.2;
	public static final double INTEGRAL_CONSTANT = 0.0;
	public static final double DERIVATIVE_CONSTANT = 0.0;
	public static final double ENCODER_DPP = 0.05;

	/** Height of a level in the unit used by encoders (in.) */
	public static final double LEVEL_HEIGHT = 12.0;

	/** Error to define when PID is complete */
	public static final double ACCEPTABLE_PID_ERROR = 1;

	/** Speed when bouncing due to Hall effect trigger */
	public static final double BOUNCE_SPEED = 0.2;

	/** Maximum Lifter Speed */
	public static final double MAX_SPEED = 1;

	/** Scaling value for teleop control */
	public static final double SPEED_SCALING = 1;

	/** Bounds on PID input values */
	public static final double INPUT_RANGE = 9999;
	public static final double ENCODER_MAX_PERIOD = 100;
}
