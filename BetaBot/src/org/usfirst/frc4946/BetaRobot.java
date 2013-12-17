/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4946;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BetaRobot extends SimpleRobot {
	
	RobotDrive m_robotDrive = new RobotDrive(RobotConstants.PWM_MOTOR_LEFT, RobotConstants.PWM_MOTOR_RIGHT);
	
	Joystick m_driveJoystick = new Joystick(RobotConstants.JOYSTICK_LEFT);
	Joystick m_taskJoystick = new Joystick(RobotConstants.JOYSTICK_RIGHT);
	
	PWM m_leftServo = new PWM(RobotConstants.PWM_LEFT_SERVO);
	PWM m_rightServo = new PWM(RobotConstants.PWM_RIGHT_SERVO);
	
	DriverStationLCD m_driverStation = DriverStationLCD.getInstance();
	
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
    	
    	m_driverStation.println(DriverStationLCD.Line.kMain6, 1, "Entering operator control");
    	m_driverStation.updateLCD();
    	
    	while (isOperatorControl() && isEnabled()){
    		//Call the drive oriented code
    		operatorDriveSystem();
    		
    		//Call the task oriented code
    		operatorTaskSystem();
    		
    	}
    	
    	m_driverStation.println(DriverStationLCD.Line.kMain6, 1, "Stopping operator control");
    	m_driverStation.updateLCD();
    	
    }

	private void operatorTaskSystem() {
		//=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
		//Get seems to return -1 to 1.
		double x = m_taskJoystick.getX();
		double y = m_taskJoystick.getY();
		
		//Expects a value of 0-1
		// Shift over by 1 and bring to range
		m_leftServo.setPosition((x+1.0)/2.0);
		m_rightServo.setPosition((y+1.0)/2.0);
	}

	private void operatorDriveSystem() {
		//m_robotDrive.arcadeDrive(m_DriveJoystick); 
		
		//=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
		//Same as above, but reduce either turning, or maximum speed depending on the trigger.
		double outputMagnitude = m_driveJoystick.getY();
		double curve = m_driveJoystick.getX();
		
		//Check the trigger
		if( m_driveJoystick.getTrigger() ){
			//allow fast speed, but reduce turning
			curve *= 0.25;
			
		}else{
			//Drive slow if the trigger is not down
			outputMagnitude *= 0.25;
			
		}
		
		m_robotDrive.arcadeDrive(outputMagnitude, curve, true);
		
	}
}
