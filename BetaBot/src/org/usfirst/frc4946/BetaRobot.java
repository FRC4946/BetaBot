/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Solenoid;

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
	
	
	
	//*** Hypothetical code for the pneumatics ***\\
	
	// The first arg is the pressure switch, which will open at 115 psi and reclose at 95. It's value will be used to activate and deactivate the relay.
	// The second is the compressor's relay (The Spike module). It is what turns on and off the compressor.
	Compressor m_primaryCompressor = new Compressor(RobotConstants.COMPRESSOR_PRESSURE_SWITCH, RobotConstants.COMPRESSOR_RELAY);
	
	
	Solenoid m_liftBallSolenoid = new Solenoid(RobotConstants.BALL_LIFT_VALVE_RELAY);
	
	// Again, should these be combined into one?
	Solenoid m_extendGrabberSolenoid = new Solenoid(RobotConstants.EXTEND_ARM_VALVE_RELAY);
	Solenoid m_retractGrabberSolenoid = new Solenoid(RobotConstants.RETRACT_ARM_VALVE_RELAY);
	
	
	
	
	
	
	/**
	 * Our constructor for the BetaRobot class
	 */
	public BetaRobot(){
		
		// Start the compressor, let it do it's thing. It will turn on and off automatically to regulate pressure.
		m_primaryCompressor.start();
	}
	
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
		
		//TODO: Decide on buttons for the pneumatic controls. Current ones are just for debug
		
		// If the trigger is down, lift the ball into the rollers
		if(m_taskJoystick.getTrigger()){
			m_liftBallSolenoid.set(True);
		}
		else {
			m_liftBallSolenoid.set(False);
		}
		
		
		// If the 1st button is pushed, extend grabber
		if(m_taskJoystick.getButton(1)){
			m_extendGrabberSolenoid.set(true);
		}
		else{
			m_retractGrabberSolenoid.set(true);
		}
		
		m_extendGrabberSolenoid.set(false);
		m_retractGrabberSolenoid.set(false);
		
		
		
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
		/*		
		//Check the joystick's deadzone
		if(Math.abs(outputMagnitude) < RobotConstants.DRIVE_JOYSTICK_DEADZONE){
			outputMagnitude = 0.0;
		}
		if(Math.abs(curve) < RobotConstants.DRIVE_JOYSTICK_DEADZONE){
			curve = 0.0;
		}
		*/
		 
		 
		 
		
		
		//Check the trigger
		if( m_driveJoystick.getTrigger() ){
			//allow fast speed, but reduce turning
			curve *= 0.4;
			
		}else{
			//Drive slow if the trigger is not down
			outputMagnitude *= 0.4;
			
		}
		
		
		
		
		m_robotDrive.arcadeDrive(outputMagnitude, curve * -1, true);
		
		
	}
}
