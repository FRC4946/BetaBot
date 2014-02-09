package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeArm {

	private SpeedController m_intakeController = new Jaguar(
			RobotConstants.PWM_MOTOR_INTAKE);

	private Solenoid m_extendGrabberSolenoid = new Solenoid(
			RobotConstants.EXTEND_ARM_VALVE_RELAY);
	private Solenoid m_retractGrabberSolenoid = new Solenoid(
			RobotConstants.RETRACT_ARM_VALVE_RELAY);

	private double speed = 0.0;
	private boolean motorsAreEnabled = false;
	private boolean armIsExtended = false;

	private int m_solenoidCounter = 0;

	public void toggleExtended() {
		armIsExtended = !armIsExtended;

		setExtended(armIsExtended);
	}

	/**
	 * Extend or retract the arm.
	 * 
	 * @param isExtended
	 *            Whether to extend or retract the arm.
	 */
	public void setExtended(boolean isExtended) {

		armIsExtended = isExtended;

		if (isExtended) {
			m_retractGrabberSolenoid.set(false);
			m_extendGrabberSolenoid.set(true);
			m_solenoidCounter = 1000;

		} else {
			m_extendGrabberSolenoid.set(false);
			m_retractGrabberSolenoid.set(true);
			m_solenoidCounter = 1000;
		}
	}
	
	public void updateSolenoids(){
		// Turn off the solenoid after 100 cycles.
		if (m_solenoidCounter == 0) {
			m_extendGrabberSolenoid.set(false);
			m_retractGrabberSolenoid.set(false);

		} else {
			m_solenoidCounter--;

		}
	}
	
	
	

	public void toggleEnabled() {
		motorsAreEnabled = !motorsAreEnabled;

		setEnabled(motorsAreEnabled);
	}

	/**
	 * Start or stop the motors. If the speed has been changed, call
	 * setEnabled(true) to update the motors.
	 * 
	 * @param isEnabled
	 *            Whether to enable the motors or not.
	 */
	public void setEnabled(boolean isEnabled) {

		motorsAreEnabled = isEnabled;

		if (isEnabled) {
			m_intakeController.set(1.0);
		} else {
			m_intakeController.set(0.0);
		}

	}

	/**
	 * Set the speed of the motors without checking the result. After calling
	 * this, call setEnabled(true) to update the motors.
	 * 
	 * @param power
	 *            The speed to set. Should be between -1.0 and 1.0
	 */
	public void setSpeedOpenLoop(double power) {

		// Make sure that the value is within the valid range of -1 to 1
		if (power >= -1.0 && power <= 1.0) {
			speed = power;
		}
	}

}
