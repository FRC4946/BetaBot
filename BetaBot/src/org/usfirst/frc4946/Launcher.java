package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Jaguar;

public class Launcher {

	private SpeedController m_launcherTopController = new Jaguar(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
	private SpeedController m_launcherBottomController = new Jaguar(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);
	
	private double speed = 0.0;
	private boolean motorsAreEnabled = false;
	
	public void toggleEnabled(){
		motorsAreEnabled = !motorsAreEnabled;
		
		setEnabled(motorsAreEnabled);
	}
	
	
	/**
	 * Start or stop the motors.
	 * If the speed has been changed, call setEnabled(true) to update the motors.
	 * 
	 * @param isEnabled Whether to enable the motors or not.
	 */
	public void setEnabled(boolean isEnabled){
		
		motorsAreEnabled = isEnabled;
		
		if(isEnabled){
			m_launcherTopController.set(speed);
			m_launcherBottomController.set(speed);
			}
		else{
			m_launcherTopController.set(0.0);
			m_launcherBottomController.set(0.0);
		}
	}

	/**
	 * Set the speed of the motors without checking the result.
	 * After calling this, call setEnabled(true) to update the motors.
	 *
	 * @param power The speed to set. Should be between -1.0 and 1.0
	 */
	public void setSpeedOpenLoop(double power){
		
		// Make sure that the value is within the valid range of -1 to 1
		if(power >= -1.0 && power <= 1.0){
			speed = power;
		}
	}
	
	
	public void setSpeedRPM(double rpm){
		
	}
	
}
