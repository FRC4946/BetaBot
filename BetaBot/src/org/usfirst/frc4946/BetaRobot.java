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
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BetaRobot extends SimpleRobot {
	
	RobotDrive m_robotDrive = new RobotDrive(RobotConstants.PWM_MOTOR_LEFT_FRONT, RobotConstants.PWM_MOTOR_LEFT_REAR, RobotConstants.PWM_MOTOR_RIGHT_FRONT, RobotConstants.PWM_MOTOR_RIGHT_REAR);
	
	IntakeArm m_intakeArm = new IntakeArm();
	Launcher m_launcher = new Launcher();
	Loader m_loader = new Loader();

	
	Joystick m_driveJoystick = new Joystick(RobotConstants.JOYSTICK_LEFT);
	Joystick m_taskJoystick = new Joystick(RobotConstants.JOYSTICK_RIGHT);
	
	DriverStationLCD m_driverStation = DriverStationLCD.getInstance();
	
		
	// The first arg is the pressure switch, which will open at 115 psi and reclose at 95. It's value will be used to activate and deactivate the relay.
	// The second is the compressor's relay (The Spike module). It is what turns on and off the compressor.
	Compressor m_primaryCompressor = new Compressor(RobotConstants.COMPRESSOR_PRESSURE_SWITCH, RobotConstants.COMPRESSOR_RELAY);
	
		
	
	boolean buttonIntakeIsDown = false;
	boolean buttonLaunchIsDown = false;
	
	boolean intakeIsRear = true;
	
	
	
	
	/**
	 * Our constructor for the BetaRobot class.
	 */
	public BetaRobot(){
		
		// Start the compressor, let it do it's thing. It will turn on and off automatically to regulate pressure.
		//m_primaryCompressor.start();
		
		// Set the speed of the motors on the launcher and intake
		m_intakeArm.setSpeedOpenLoop(1.0);
		m_launcher.setSpeedOpenLoop(1.0);
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
    	
    	m_launcher.setEnabled(true);
    	
    	Timer.delay(0.1);
    	
    	m_loader.setLiftBall(true);
    	
    	Timer.delay(0.8);
    	
    	m_launcher.setEnabled(false);
    	m_loader.setLiftBall(false);
    	
    	
    	
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

    	
    	m_primaryCompressor.start();
    	
    	while (isOperatorControl() && isEnabled()){
    		
    		
    		//Call the drive oriented code
    		operatorDriveSystem();
    		
    		//Call the task oriented code
    		operatorTaskSystem();
    		
    		m_driverStation.updateLCD();
    		
    	}
    	
    	m_driverStation.println(DriverStationLCD.Line.kMain6, 1, "Stopping operator control");
		m_driverStation.updateLCD();

    }

    
    
    /**
     * This function contains code pertaining to the task joystick.
     * It is called once every loop of the operator control cycle.
     */
	private void operatorTaskSystem() {		
		//TODO: Decide on buttons for the pneumatic controls. Current ones are just for debug
		
		
		
		
		//********* INTAKE *********\\
		

		// If the intake button is pressed, get ready for its release
		if (m_taskJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_INTAKE)){
			buttonIntakeIsDown = true;
		}
		
		// If the intake button is released, toggle the state of the solenoid
		if (!m_taskJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_INTAKE) &&
				buttonIntakeIsDown == true){
			
			
			buttonIntakeIsDown = false;
			m_intakeArm.toggleEnabled();
			m_intakeArm.toggleExtended();
		}
		
				
		m_intakeArm.updateSolenoids();

		
		
		
		
		
		
		//********* LOADER *********\\
		
		
		// If the trigger is down, lift the ball into the rollers		
		m_loader.setExtended(m_taskJoystick.getTrigger());
		
		m_loader.updateSolenoids();

		
		
		
		
		
		
		
		
		//********* LAUNCHER *********\\
		
		
		// If the launch button is pressed, get ready for its release
		if (m_taskJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_LAUNCH)){
			buttonLaunchIsDown = true;
		}
		
		// If the launch button is released, toggle the state of the solenoid
		if (!m_taskJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_LAUNCH) &&
				buttonLaunchIsDown == true){
			
			buttonLaunchIsDown = false;
			m_launcher.toggleEnabled();
		}
		
		
		// Set the launcher speed to the Z val, and then update the motors
		
		double launcherSpeed = (m_taskJoystick.getZ() + 1.0) / 2;
		
		m_launcher.setSpeedOpenLoop(launcherSpeed);
		m_launcher.setEnabled(m_launcher.isEnabled());
		
		m_driverStation.println(DriverStationLCD.Line.kUser5, 1, "Speed is "+launcherSpeed+"                 ");		

		
	}

    /**
     * This function contains code pertaining to the drive joystick.
     * It is called once every loop of the operator control cycle.
     */
	private void operatorDriveSystem() {
		
		//Drive the robot with either better turning, or maximum speed depending on the trigger.
		double outputMagnitude = m_driveJoystick.getY();
		double curve = m_driveJoystick.getX();
		
		if( m_driveJoystick.getTrigger() ){
			//allow fast speed, but reduce turning
			curve *= 0.4;
			
		}else{
			//Drive slow if the trigger is not down
			outputMagnitude *= 0.4;
			
		}
		
		
		
		// Set the orientation (which way if front)
		if (m_driveJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_SHOOT_ORIENTATION)){
			intakeIsRear = true;
		}
		else if (m_driveJoystick.getRawButton(RobotConstants.JOYSTICK_BUTTON_INTAKE_ORIENTATION)){
			intakeIsRear = false;
		}
		
		// Call arcadeDrive with the updated orientation
		if(intakeIsRear){
			m_robotDrive.arcadeDrive(outputMagnitude, curve * -1, true);
		}
		else{
			m_robotDrive.arcadeDrive(outputMagnitude * -1, curve * -1, true);
		}
		
		
		//********* CONSOLE *********\\
		
		m_driverStation.println(DriverStationLCD.Line.kUser2, 1, "Z is "+m_taskJoystick.getZ()+"                 ");
		
		m_driverStation.println(DriverStationLCD.Line.kUser3, 1, "Y value is "+outputMagnitude+"                 ");
		m_driverStation.println(DriverStationLCD.Line.kUser4, 1, "X value is "+curve+"                 ");		
		
	}
}
