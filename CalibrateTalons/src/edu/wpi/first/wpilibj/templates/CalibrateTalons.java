/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CalibrateTalons extends SimpleRobot {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    private Talon m_launcherTopController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
    private Talon m_launcherBottomController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);
    private Talon m_launcherIntakeController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_INTAKE);
    
    Joystick m_taskJoystick = new Joystick(RobotConstants.JOYSTICK_RIGHT);
    
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
       
        while (isOperatorControl() && isEnabled()){
            
            if (m_taskJoystick.getRawButton(1)){
                m_launcherTopController.set(1.0);
                m_launcherBottomController.set(1.0);
                m_launcherIntakeController.set(1.0);
            }
            if (m_taskJoystick.getRawButton(2)){
                m_launcherTopController.set(-1);
                m_launcherBottomController.set(-1);
                m_launcherIntakeController.set(-1); 
            }
            if (m_taskJoystick.getRawButton(3)){
                m_launcherTopController.set(0);
                m_launcherBottomController.set(0);
                m_launcherIntakeController.set(0); 
            }
    		
    	}
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
