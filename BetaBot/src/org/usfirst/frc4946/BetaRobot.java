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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BetaRobot extends SimpleRobot {
	
	RobotDrive m_robotDrive = new RobotDrive(RobotConstants.PWM_MOTOR_LEFT_FRONT, RobotConstants.PWM_MOTOR_LEFT_REAR, RobotConstants.PWM_MOTOR_RIGHT_FRONT, RobotConstants.PWM_MOTOR_RIGHT_REAR);
	
	
	// These are motors for the launcher (Well really they're the speed controllers <i>attached</i> to the motors, but close enough)
	SpeedController m_launcherTopController = new Jaguar(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
	SpeedController m_launcherBottomController = new Jaguar(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);

	
	Joystick m_driveJoystick = new Joystick(RobotConstants.JOYSTICK_LEFT);
	Joystick m_taskJoystick = new Joystick(RobotConstants.JOYSTICK_RIGHT);
	
	DriverStationLCD m_driverStation = DriverStationLCD.getInstance();
	
	
	
	//*** Hypothetical code for the pneumatics ***\\
	
	// The first arg is the pressure switch, which will open at 115 psi and reclose at 95. It's value will be used to activate and deactivate the relay.
	// The second is the compressor's relay (The Spike module). It is what turns on and off the compressor.
	Compressor m_primaryCompressor = new Compressor(RobotConstants.COMPRESSOR_PRESSURE_SWITCH, RobotConstants.COMPRESSOR_RELAY);
	
	
	Solenoid m_liftBallSolenoid = new Solenoid(RobotConstants.BALL_LIFT_VALVE_RELAY);
	
	Solenoid m_extendGrabberSolenoid = new Solenoid(RobotConstants.EXTEND_ARM_VALVE_RELAY);
	Solenoid m_retractGrabberSolenoid = new Solenoid(RobotConstants.RETRACT_ARM_VALVE_RELAY);
	
	
	
	boolean buttonIntakeIsDown = false;
	
	
	
	
	
	/**
	 * Our constructor for the BetaRobot class.
	 */
	public BetaRobot(){
		
		// Start the compressor, let it do it's thing. It will turn on and off automatically to regulate pressure.
		m_primaryCompressor.start();
	}
	
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
            	
    	m_driverStation.println(DriverStationLCD.Line.kMain6, 1, "Entering autonomous");
    	m_driverStation.updateLCD();
    	
    	
    	//TODO: The autonomous stuff
    	// Check for hotzone?
    	// Aim towards hotzone?
    	// Shoot the ball
    	// Move forwards
    	
    	
    	
    	/* 
    	
    	//TODO: Hotzone stuff?
    	
    	
    	// Shoot the ball
    	
    	m_launcherTopController.set(1.0);
    	m_launcherBottomController.set(1.0);
    	
    	Timer.delay(0.1);
    	
    	m_liftBallSolenoid.set(true);
    	
    	Timer.delay(0.8);
    	
    	m_liftBallSolenoid.set(false);
    	
    	
    	
    	// Move forwards
    	
    	m_robotDrive.drive(0.5, 0);
    	
    	Timer.delay(3);
    	
    	m_robotDrive.drive(0,0);
    	

    	
    	*/
    	
    	
    	m_driverStation.println(DriverStationLCD.Line.kMain6, 1, "Finished auto, waiting");
    	m_driverStation.updateLCD();
    	
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

    
    
    /**
     * This function contains code pertaining to the task joystick.
     * It is called once every loop of the operator control cycle.
     */
	private void operatorTaskSystem() {
		//=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
		
		//TODO: Decide on buttons for the pneumatic controls. Current ones are just for debug
		
		// If the trigger is down, lift the ball into the rollers
		if(m_taskJoystick.getTrigger()){
			m_liftBallSolenoid.set(true);
		}
		else {
			m_liftBallSolenoid.set(false);
		}
		
		
		
		// If the intake button is pressed, get ready for its release
		if (m_taskJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_INTAKE)){
			buttonIntakeIsDown = true;
		}
		
		// If the intake button is released, toggle the state of the solenoid
		if (!m_taskJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_INTAKE) &&
				buttonIntakeIsDown == true){
			buttonIntakeIsDown = false;
			
			
			
			isExtended = !isExtended;
			
			if (isExtended){
				m_extendGrabberSolenoid.set(true);
			}
			else{
				m_retractGrabberSolenoid.set(true);
			}
			
			Timer.delay(0.1);
			m_extendGrabberSolenoid.set(false);
			m_retractGrabberSolenoid.set(false);
			
			
			
			
			// Another way of doing the above, not sure which is better. This one will, however, make the solenoid stay powered, which is a waste but I don't think it will cause any problems
			
			/*
			if (isExtended){
				m_retractGrabberSolenoid.set(false);
				Timer.delay(0.1);
				m_extendGrabberSolenoid.set(true);
			}
			else{
				m_extendGrabberSolenoid.set(false);
				Timer.delay(0.1);
				m_retractGrabberSolenoid.set(true);
			}
			*/
		}
	}

    /**
     * This function contains code pertaining to the drive joystick.
     * It is called once every loop of the operator control cycle.
     */
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
