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
	
	RobotDrive m_robotDrive = new RobotDrive(RobotConstants.PWM_MOTOR_LEFT_FRONT, RobotConstants.PWM_MOTOR_LEFT_REAR, RobotConstants.PWM_MOTOR_RIGHT_FRONT, RobotConstants.PWM_MOTOR_RIGHT_REAR);
	
	
	Joystick m_driveJoystick = new Joystick(RobotConstants.JOYSTICK_LEFT);
	Joystick m_taskJoystick = new Joystick(RobotConstants.JOYSTICK_RIGHT);
	
	
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
    		
    		buttonTest();
    		
    		//Call the drive oriented code
    		operatorDriveSystem();
    		
    		//Call the task oriented code
    		operatorTaskSystem();
    		
    	}
    	
    	m_driverStation.println(DriverStationLCD.Line.kMain6, 1, "Stopping operator control");
    	m_driverStation.updateLCD();
    	
    }


    public void buttonTest() {
    	
    		for(int i=0;i<11;i++)
    		{
				if (m_driveJoystick.getRawButton(i)){
	    			
	    			m_driverStation.println(DriverStationLCD.Line.kUser2, 1, "Button is "+i+"  ");
	    			m_driverStation.updateLCD();
	    		
	    		}
    		}
    		
    }
    
	private void operatorTaskSystem() {
		//=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
		
		//Code for the 'Task' joystick will go here
	}

	private void operatorDriveSystem() {
		//m_robotDrive.arcadeDrive(m_driveJoystick); 
		
		//=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
		//Same as above, but reduce either turning, or maximum speed depending on the trigger.
		
		
		double outputMagnitude = m_driveJoystick.getY();
		double curve = m_driveJoystick.getX();
				
		m_driverStation.println(DriverStationLCD.Line.kUser3, 1, "Y value is "+outputMagnitude+"                 ");
		m_driverStation.println(DriverStationLCD.Line.kUser4, 1, "X value is "+curve+"                 ");
		m_driverStation.updateLCD();
				
		//Check the joystick's deadzone
		if(Math.abs(outputMagnitude) < RobotConstants.DRIVE_JOYSTICK_DEADZONE){
			outputMagnitude = 0.0;
		}
		if(Math.abs(curve) < RobotConstants.DRIVE_JOYSTICK_DEADZONE){
			curve = 0.0;
		}
		
		
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
