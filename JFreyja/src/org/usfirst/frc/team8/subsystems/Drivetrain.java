package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	/**
	 * Keeps track of the State of the Drivetrain
	 * for use in the update method
	 *
	 * TELEOP - Used for standard human controlled operation
	 * AUTOMATED_DRIVE - Used when driving a set distance
	 * AUTOMATED_ROTATE - Used when rotating a set angle
	 * IDLE - Used when no motion is desired and no joystick controls have been made
	 * BRAKING - Used to automatically stop the robot
	 * STOPPED - Used for testing, no code running
	 */
	enum State{
		IDLE,
		TELEOP,
		AUTOMATED_DRIVE,
		AUTOMATED_ROTATE,
		BRAKING,
		STOPPED
	};	
	State state = State.IDLE;
	
	//PID constants
	public static final double DRIVE_PROPORTIONAL = 0.12;
	public static final double DRIVE_INTEGRAL = 0.0;
	public static final double DRIVE_DERIVATIVE = 0.1;

	 //Gyro PID constants
	public static final double GYRO_PROPORTIONAL = 0.12;
	public static final double GYRO_INTEGRAL = 0.0;
	public static final double GYRO_DERIVATIVE = 0.1;

	//Encoder constants
	public static final double RIGHT_DPP = 0.1545595;
	public static final double LEFT_DPP = 0.1577287;
	public static final double ENCODER_INPUT_RANGE = 999;
	public static final double ENCODER_DRIVE_OUTPUT_RANGE = 999;
	public static final double ENCODER_GYRO_OUTPUT_RANGE = 999;
	
	//Talons
	Talon leftTalon1 = new Talon(Ports.PORT_DRIVETRAIN_TALON_LEFT_FRONT);
	Talon leftTalon2 = new Talon(Ports.PORT_DRIVETRAIN_TALON_LEFT_BACK);
	Talon rightTalon1 = new Talon(Ports.PORT_DRIVETRAIN_TALON_RIGHT_FRONT);
	Talon rightTalon2 = new Talon(Ports.PORT_DRIVETRAIN_TALON_RIGHT_BACK);

	//Sensors
	Gyro gyroscope = new Gyro(Ports.PORT_GYROSCOPE);
	
	//Encoders
	Encoder leftEncoder = new Encoder(Ports.PORT_DRIVETRAIN_ENCODER_LEFT_A, Ports.PORT_DRIVETRAIN_ENCODER_LEFT_B, true);
	Encoder rightEncoder = new Encoder(Ports.PORT_DRIVETRAIN_ENCODER_RIGHT_A, Ports.PORT_DRIVETRAIN_ENCODER_RIGHT_B, false);
	
	//PID Controllers
	PIDController leftGyroscopeController1 = new PIDController(GYRO_PROPORTIONAL, GYRO_INTEGRAL, GYRO_DERIVATIVE, gyroscope, leftTalon1);
	PIDController leftGyroscopeController2 = new PIDController(GYRO_PROPORTIONAL, GYRO_INTEGRAL, GYRO_DERIVATIVE, gyroscope, leftTalon2);
	PIDController rightGyroscopeController1 = new PIDController(GYRO_PROPORTIONAL, GYRO_INTEGRAL, GYRO_DERIVATIVE, gyroscope, rightTalon1);
	PIDController rightGyroscopeController2 = new PIDController(GYRO_PROPORTIONAL, GYRO_INTEGRAL, GYRO_DERIVATIVE, gyroscope, rightTalon2);
	
	PIDController leftDriveController1 = new PIDController(DRIVE_PROPORTIONAL, DRIVE_INTEGRAL, DRIVE_DERIVATIVE, leftEncoder, leftTalon1);
	PIDController leftDriveController2 = new PIDController(DRIVE_PROPORTIONAL, DRIVE_INTEGRAL, DRIVE_DERIVATIVE, leftEncoder, leftTalon2);
	PIDController rightDriveController1 = new PIDController(DRIVE_PROPORTIONAL, DRIVE_INTEGRAL, DRIVE_DERIVATIVE, leftEncoder, rightTalon1);
	PIDController rightDriveController2 = new PIDController(DRIVE_PROPORTIONAL, DRIVE_INTEGRAL, DRIVE_DERIVATIVE, leftEncoder, rightTalon2);	
}
