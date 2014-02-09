package org.usfirst.frc4946;

import org.usfirst.frc4946.closedLoop.PIDRateController;
import org.usfirst.frc4946.closedLoop.RateCounter;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Jaguar;

public class Launcher {

	
	private SpeedController m_launcherBottomController = new Jaguar(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);
	
	private double speed = 0.0;
	private boolean motorsAreEnabled = false;
	
	private boolean isClosedLoopControl = false;
	
	//*************************
	// Closed loop control test
	private SpeedController m_launcherTopController = new Jaguar(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
	RateCounter m_TopCounter = new RateCounter(RobotConstants.PWM_DIO_HALLSENSOR_LAUNCHER_TOP);
	
	double m_KP = 1;
	double m_KD = 0;
	double m_ClosedLoopTolerance = 10;
	
	PIDRateController m_TopPID = new PIDRateController(m_KP,  0,  m_KD, m_TopCounter, m_launcherTopController);
	//*************************
	
	public void toggleEnabled(){
		motorsAreEnabled = !motorsAreEnabled;
		setEnabled(motorsAreEnabled);
		
	}
	
	public double getTopRollerRPM(){
		return m_TopCounter.getRPM();
		
	}
	
	public void setClosedLoopEnabled(boolean isEnabled){
		isClosedLoopControl = isEnabled;
		motorsAreEnabled = isEnabled;
		
		if( isEnabled ){
			m_TopPID.enable();
			m_TopPID.setTolerance(m_ClosedLoopTolerance);
			
		}else{
			m_TopPID.disable();
			
		}		
	} 
	
	/**
	 * Start or stop the motors.
	 * If the speed has been changed, call setEnabled(true) to update the motors.
	 * 
	 * @param isEnabled Whether to enable the motors or not.
	 */
	public void setEnabled(boolean isEnabled){
		isClosedLoopControl = false;
		
		motorsAreEnabled = isEnabled;
		
		if(isEnabled){
			m_launcherTopController.set(speed);
			m_launcherBottomController.set(speed*-1);
		
		}else{
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
		isClosedLoopControl = false;
		
		// Make sure that the value is within the valid range of -1 to 1
		if(power >= -1.0 && power <= 1.0){
			speed = power;
		}
	}
	
	
	public void setSpeedRPM(double rpm){
		m_TopPID.setSetpoint(rpm);
		
	}
	
	
	public boolean isEnabled(){
		return motorsAreEnabled;

	}

	public boolean isClosedLoopControl() {
		return isClosedLoopControl;
	}

}
