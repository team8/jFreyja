package stubs;

public class PIDController {
	public PIDController(double gyroProportional, double gyroIntegral, double gyroDerivative, Encoder leftEncoder,
			Talon leftTalon1) {
		// TODO Auto-generated constructor stub
	}
	public PIDController(double gyroProportional, double gyroIntegral, double gyroDerivative, Gyro gyroscope,
			Talon leftTalon1) {
		// TODO Auto-generated constructor stub
	}
	public PIDController() {
		// TODO Auto-generated constructor stub
	}
	public double getError() {
		return 1;
	}
	public void disable() {}
	public void enable() {}
	public void setSetpoint(double setpoint) {}
	public void setInputRange(double d, double encoderInputRange) {
		// TODO Auto-generated method stub
		
	}
	public void setOutputRange(double d, double encoderDriveOutputRange) {
		// TODO Auto-generated method stub
		
	}
}