package com.palyrobotics.robot;

public class Constants {
	class Drivetrain {
		public static final int PID_TOLERANCE = 1;		
	}
	class Ports {
		/** Pulse Width Modulation */
		public static final int DRIVETRAIN_TALON_LEFT_FRONT = 3;
		public static final int DRIVETRAIN_TALON_LEFT_BACK = 2;
		public static final int DRIVETRAIN_TALON_RIGHT_FRONT = 1;
		public static final int DRIVETRAIN_TALON_RIGHT_BACK = 0;
		
		/** Laptop USB */
		public static final int JOYSTICK_OPERATOR = 0;
		public static final int JOYSTICK_DRIVE = 1;
		public static final int JOYSTICK_TURN = 2;
	}
}
