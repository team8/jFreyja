package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Talon;
 /**
  * Drivetrain class, extends Subsystem
  * Contains methods and constants pertaining to the drivetrain
  * Operates the drivetrain using Joysticks or PID
  * 
  * Has encoders, a gyroscope, and uses talons for speed controllers
  * @see Subsystem
  *
  */
public class Drivetrain extends Subsystem {
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
	private enum State{
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
	
	// MAX_FORWARD_SPEED + MAX_TURN_SPEED should not exceed 1.0
	//Constants for regulating speed
	private static double MAX_FORWARD_SPEED = 0.5;
	private static double MAX_TURN_SPEED = 0.3;
	private static double SPEED_SCALING = 1.0;
	private static double TURN_SCALING = 1.0;

	//Acceptable error constants
	private static double ACCEPTABLE_DRIVE_ERROR = 1;
	private static double ACCEPTABLE_ROTATE_ERROR = 1;
	private static double ACCEPTABLE_BRAKE_ERROR = 0.01;

	// Max period for which
	private static int ENCODER_MAX_PERIOD = 100;
	
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
	PIDController leftGyroController1 = new PIDController(GYRO_PROPORTIONAL, GYRO_INTEGRAL, GYRO_DERIVATIVE, gyroscope, leftTalon1);
	PIDController leftGyroController2 = new PIDController(GYRO_PROPORTIONAL, GYRO_INTEGRAL, GYRO_DERIVATIVE, gyroscope, leftTalon2);
	PIDController rightGyroController1 = new PIDController(-GYRO_PROPORTIONAL, -GYRO_INTEGRAL, -GYRO_DERIVATIVE, gyroscope, rightTalon1);
	PIDController rightGyroController2 = new PIDController(-GYRO_PROPORTIONAL, -GYRO_INTEGRAL, -GYRO_DERIVATIVE, gyroscope, rightTalon2);
	
	PIDController leftDriveController1 = new PIDController(DRIVE_PROPORTIONAL, DRIVE_INTEGRAL, DRIVE_DERIVATIVE, leftEncoder, leftTalon1);
	PIDController leftDriveController2 = new PIDController(DRIVE_PROPORTIONAL, DRIVE_INTEGRAL, DRIVE_DERIVATIVE, leftEncoder, leftTalon2);
	PIDController rightDriveController1 = new PIDController(-DRIVE_PROPORTIONAL, -DRIVE_INTEGRAL, -DRIVE_DERIVATIVE, rightEncoder, rightTalon1);
	PIDController rightDriveController2 = new PIDController(-DRIVE_PROPORTIONAL, -DRIVE_INTEGRAL, -DRIVE_DERIVATIVE, rightEncoder, rightTalon2);	
	public Drivetrain() {
		leftEncoder.setDistancePerPulse(LEFT_DPP);
		rightEncoder.setDistancePerPulse(RIGHT_DPP);
		leftEncoder.setMaxPeriod(ENCODER_MAX_PERIOD);
		rightEncoder.setMaxPeriod(ENCODER_MAX_PERIOD);
	
		leftDriveController1.setInputRange(-ENCODER_INPUT_RANGE, ENCODER_INPUT_RANGE);
		leftDriveController2.setInputRange(-ENCODER_INPUT_RANGE, ENCODER_INPUT_RANGE);
		leftDriveController1.setOutputRange(-ENCODER_DRIVE_OUTPUT_RANGE, ENCODER_DRIVE_OUTPUT_RANGE);
		leftDriveController2.setOutputRange(-ENCODER_DRIVE_OUTPUT_RANGE, ENCODER_DRIVE_OUTPUT_RANGE);
		
		rightDriveController1.setInputRange(-ENCODER_INPUT_RANGE, ENCODER_INPUT_RANGE);
		rightDriveController2.setInputRange(-ENCODER_INPUT_RANGE, ENCODER_INPUT_RANGE);
		rightDriveController1.setOutputRange(-ENCODER_DRIVE_OUTPUT_RANGE, ENCODER_DRIVE_OUTPUT_RANGE);
		rightDriveController2.setOutputRange(-ENCODER_DRIVE_OUTPUT_RANGE, ENCODER_DRIVE_OUTPUT_RANGE);
		
		leftGyroController1.setOutputRange(-ENCODER_GYRO_OUTPUT_RANGE, ENCODER_GYRO_OUTPUT_RANGE);
		rightGyroController1.setOutputRange(-ENCODER_GYRO_OUTPUT_RANGE, ENCODER_GYRO_OUTPUT_RANGE);
		leftGyroController2.setOutputRange(-ENCODER_GYRO_OUTPUT_RANGE, ENCODER_GYRO_OUTPUT_RANGE);
		rightGyroController2.setOutputRange(-ENCODER_GYRO_OUTPUT_RANGE, ENCODER_GYRO_OUTPUT_RANGE);
	}
	
	public void init() {
		leftEncoder.reset();
		rightEncoder.reset();
		gyroscope.initGyro();
	}
	
	public void update() {
		debug();
		
		switch(state) {
		case IDLE:
			break;
		case TELEOP:
			break;
		case AUTOMATED_DRIVE:
			if(encodersStopped() && driveControllerError() < ACCEPTABLE_DRIVE_ERROR) {
				idle();
			}
			break;
		case AUTOMATED_ROTATE:
			if(encodersStopped() && gyroControllerError() < ACCEPTABLE_ROTATE_ERROR) {
				idle();
			}
			break;
		case BRAKING:
			if(encodersStopped() && driveControllerError() < ACCEPTABLE_BRAKE_ERROR) {
				idle();
			}
			break;
		case STOPPED:
			break;
		}
	}
	
	public void disable() {
		disableControllers();
		setState(State.STOPPED);
	}
	
	public void idle() {
		disableControllers();
		setState(State.IDLE);
	}
	
	public boolean isIdle() {
		return state == State.IDLE;
	}
	
	public void drive(double turnValue, double forwardValue) {
		setState(State.TELEOP);
		
		disableControllers();
		
		double scaledForward = Math.max(Math.min(SPEED_SCALING * forwardValue, MAX_FORWARD_SPEED), -MAX_FORWARD_SPEED);
		double scaledTurn = Math.max(Math.min(TURN_SCALING * turnValue, MAX_TURN_SPEED), -MAX_TURN_SPEED);
		
		leftTalon1.set(-(scaledForward - scaledTurn));
		leftTalon2.set(-(scaledForward - scaledTurn));
		rightTalon1.set(scaledForward + scaledTurn);
		rightTalon2.set(scaledForward + scaledTurn);
	}
	
	public void driveDist(double distance) {
		setState(State.AUTOMATED_DRIVE);
		
		disableGyroControllers();
		
		leftEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		rightEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		
		leftDriveController1.setSetpoint(distance);
		leftDriveController2.setSetpoint(distance);
		rightDriveController1.setSetpoint(distance);
		rightDriveController2.setSetpoint(distance);
		
		enableDriveControllers();
	}
	
	public void rotateAngle(double angle) {
		setState(State.AUTOMATED_ROTATE);
		
		disableDriveControllers();
		
		leftEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		rightEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		
		leftGyroController1.setSetpoint(angle);
		leftGyroController2.setSetpoint(angle);
		rightGyroController1.setSetpoint(angle);
		rightGyroController2.setSetpoint(angle);
		
		enableGyroControllers();
	}
	
	public void brake() {
		setState(State.BRAKING);
		
		disableGyroControllers();
		
		leftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		
		leftDriveController1.setSetpoint(0);
		leftDriveController2.setSetpoint(0);
		rightDriveController1.setSetpoint(0);
		rightDriveController2.setSetpoint(0);
		
		enableDriveControllers();
	}
	/**
	 * Sets the State of Drivetrain as long as it is not in STOPPED
	 */
	private void setState(State state) {
		if(state != State.STOPPED) {
			this.state = state;
		}
	}
	/**
	 * Returns true if the encoders are stopped
	 * @return if both encoders are stopped
	 */
	private boolean encodersStopped() {
		return leftEncoder.getStopped() && rightEncoder.getStopped();
	}
	/**
	 * Gets the maximum error of the drive PIDControllers
	 * @return the error of the drive PIDControllers
	 */
	public double driveControllerError() {
		double maxLeftError = Math.max(leftDriveController1.getError(), leftDriveController2.getError());
		double maxRightError = Math.max(rightDriveController1.getError(), rightDriveController2.getError());

		return Math.max(maxLeftError, maxRightError);
	}
	/**
	 * Gets the maximum error of the gyro PIDControllers
	 * @return the error of the gyro PIDControllers
	 */
	public double gyroControllerError() {
		double maxLeftError = Math.max(leftGyroController1.getError(), leftGyroController2.getError());
		double maxRightError = Math.max(rightGyroController1.getError(), rightGyroController2.getError());

		return Math.max(maxLeftError, maxRightError);
	}
	/**
	 * Enables the gyro PIDControllers that rotate the robot
	 */
	private void enableGyroControllers() {
		leftGyroController1.enable();
		leftGyroController2.enable();
		rightGyroController1.enable();
		rightGyroController2.enable();
	}
	/**
	 * Enables the drive PIDControlers that drive and brake the robot
	 */
	private void enableDriveControllers() {
		leftDriveController1.enable();
		leftDriveController2.enable();
		rightDriveController1.enable();
		rightDriveController2.enable();
	}
	/**
	 * Disables all the PIDControllers
	 */
	private void disableControllers() {
		disableDriveControllers();
		disableGyroControllers();
	}
	/**
	 * Disables the gyro PIDControllers that rotate the robot
	 */
	private void disableGyroControllers() {
		leftGyroController1.disable();
		leftGyroController2.disable();
		rightGyroController1.disable();
		rightGyroController2.disable();
	}
	/**
	 * Disables the drive PIDControllers that drive and brake the robot
	 */
	private void disableDriveControllers() {
		leftDriveController1.disable();
		leftDriveController2.disable();
		rightDriveController1.disable();
		rightDriveController2.disable();
	}
	/**
	 * Prints general debugging information
	 */
	private void debug() {
//		System.out.println("Drivetrain Debug: ");
	}
}
