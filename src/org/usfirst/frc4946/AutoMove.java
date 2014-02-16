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
        driveDistance(6*12, 0.4);
        
        if (atDistance(6*12)) {
            //celebrate we drove distance
            extendLoader();
            //do donuts
            drive(0, 0.5);
        }
    }
}
