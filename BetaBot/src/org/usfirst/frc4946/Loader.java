package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.Solenoid;

public class Loader {

	private Solenoid m_liftBallSolenoid = new Solenoid(RobotConstants.BALL_LIFT_VALVE_RELAY);
	
	
	/**
	 * Raise or lower the loader mechanism.
	 * 
	 * @param lift Whether to raise the mechanism or not
	 */
	public void setLiftBall(boolean lift){
		
		m_liftBallSolenoid.set(lift);
	}
}
