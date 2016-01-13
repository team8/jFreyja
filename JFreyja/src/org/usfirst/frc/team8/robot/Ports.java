package org.usfirst.frc.team8.robot;

public class Ports {
	/** Laptop USB */
	public static final int PORT_JOYSTICK_OPERATOR = 0;
	public static final int PORT_JOYSTICK_DRIVE = 1;
	public static final int PORT_JOYSTICK_TURN = 2;

	/** Pulse Width Modulation */
	public static final int PORT_DRIVETRAIN_TALON_LEFT_FRONT = 3;
	public static final int PORT_DRIVETRAIN_TALON_LEFT_BACK = 0;
	public static final int PORT_DRIVETRAIN_TALON_RIGHT_FRONT = 1;
	public static final int PORT_DRIVETRAIN_TALON_RIGHT_BACK = 2;

	public static final int PORT_LIFTER_VICTOR_1 = 8;
	public static final int PORT_LIFTER_VICTOR_2 = 9;

	/** Digital Input/Output */
	public static final int PORT_DRIVETRAIN_ENCODER_LEFT_A = 2;
	public static final int PORT_DRIVETRAIN_ENCODER_LEFT_B = 3;
	public static final int PORT_DRIVETRAIN_ENCODER_RIGHT_A = 0;
	public static final int PORT_DRIVETRAIN_ENCODER_RIGHT_B = 1;

	public static final int PORT_LIFTER_ENCODER_A = 9;
	public static final int PORT_LIFTER_ENCODER_B = 8;
	
	/** Analog Input */
	public static final int PORT_GYROSCOPE = 1;
	public static final int PORT_LIFTER_HALL_EFFECT_TOP = 6;
	public static final int PORT_LIFTER_HALL_EFFECT_BOTTOM = 7;
}
