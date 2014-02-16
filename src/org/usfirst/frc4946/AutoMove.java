/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Stefan
 */
public class AutoMove extends AutoMode {
    
    public AutoMove(RobotDrive drive, Launcher launcher, Loader loader, IntakeArm intakeArm, DistanceSensor distanceSensor) {
        super(drive, launcher, loader, intakeArm, distanceSensor);
    }

    public void init() {
        
    }

    public void run() {
        driveToDistance(12*12, 0.4);
        m_driverStation.println(RobotConstants.AUTO_LCD_DRIVER, 1, "Driving to 12 ft at 0.4        ");
        
        if (atDistance(12*12)) {
            //celebrate we drove distance
            m_driverStation.println(RobotConstants.AUTO_LCD_DRIVER, 1, "AT DIST 12ft                  ");
            extendLoader();
            //do donuts
            //drive(0, 0.5);
        }
    }
}
