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
public class AutoMoveAndShoot extends AutoMode {

    AutoMode autoRoutine = new AutoMode(m_robotDrive, m_launcher, m_loader, m_intakeArm, m_distanceSensor);

    public AutoMoveAndShoot(RobotDrive drive, Launcher launcher, Loader loader, IntakeArm intakeArm, DistanceSensor distanceSensor) {
        super(drive, launcher, loader, intakeArm, distanceSensor);
    }

    public void init() {
        autoRoutine.extendArm();
        autoRoutine.startShooter();
        autoRoutine.setShooterSpeed(2500, true);
    }

    public void run() {
        autoRoutine.driveToDistance(11, 0.4);
        if (atDistance(11)&&autoRoutine.getCurrentShooterSpeed() >= 2300 && autoRoutine.getCurrentShooterSpeed() <= 2700) {
            autoRoutine.extendLoader();
        }
    }
}
