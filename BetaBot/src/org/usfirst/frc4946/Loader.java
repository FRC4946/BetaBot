package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Solenoid;

public class Loader {

	private Solenoid m_liftBallSolenoid = new Solenoid(RobotConstants.BALL_LIFT_VALVE_RELAY);
	private Solenoid m_lowerBallSolenoid = new Solenoid(RobotConstants.BALL_LOWER_VALVE_RELAY);
	
	private int m_solenoidCounter = 0;

	
	/**
	 * Raise or lower the loader mechanism.
	 * 
	 * @param lift Whether to raise the mechanism or not
	 */
	public void setExtended(boolean isExtended) {

		if (isExtended) {
			m_lowerBallSolenoid.set(false);
			m_liftBallSolenoid.set(true);
			m_solenoidCounter = 1000;

		} else {
			m_liftBallSolenoid.set(false);
			m_lowerBallSolenoid.set(true);
			m_solenoidCounter = 1000;
		}
	}
	
	public void updateSolenoids(){
		// Turn off the solenoid after 100 cycles.
		if (m_solenoidCounter == 0) {
			m_liftBallSolenoid.set(false);
			m_lowerBallSolenoid.set(false);

		} else {
			m_solenoidCounter--;

		}
	}
	
}
