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
public class AutoTwoBall extends AutoMode {

    AutoMode autoRoutine = new AutoMode(m_robotDrive, m_launcher, m_loader, m_intakeArm, m_distanceSensor);
    int ballsFired = 0;

    public AutoTwoBall(RobotDrive drive, Launcher launcher, Loader loader, IntakeArm intakeArm, DistanceSensor distanceSensor) {
        super(drive, launcher, loader, intakeArm, distanceSensor);
    }

    public void init() {
        autoRoutine.extendArm();
        autoRoutine.startShooter();
        autoRoutine.setShooterSpeed(1800, true);
    }

    public void run() {
        if (ballsFired == 0) {
            if (autoRoutine.getCurrentShooterSpeed() >= 1700 && autoRoutine.getCurrentShooterSpeed() <= 1900) {
                autoRoutine.extendLoader();
                ballsFired++;
                autoRoutine.setShooterSpeed(1800, true);
            }
        }
        if (ballsFired == 1) {
            autoRoutine.enableRollers();
            autoRoutine.driveToDistance((18.5 * 12.0), 0.4);
            if (autoRoutine.atDistance(18.5 * 12.0)) {
                ballsFired++;
            }

        }
        if (ballsFired == 2) {
            autoRoutine.disableRollers(); 
            autoRoutine.driveToDistance((16 * 12.0), 0.4);
            if (autoRoutine.atDistance(16 * 12.0) && autoRoutine.getCurrentShooterSpeed() >= 1700 && autoRoutine.getCurrentShooterSpeed() <= 1900) {
                autoRoutine.extendLoader();

            }

        }

    }

}
