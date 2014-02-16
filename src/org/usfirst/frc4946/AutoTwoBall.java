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

    //AutoMode autoRoutine = new AutoMode(m_robotDrive, m_launcher, m_loader, m_intakeArm, m_distanceSensor);
    int ballsFired = 0;

    public AutoTwoBall(RobotDrive drive, Launcher launcher, Loader loader, IntakeArm intakeArm, DistanceSensor distanceSensor) {
        super(drive, launcher, loader, intakeArm, distanceSensor);
    }

    public void init() {
        extendArm();
        startShooter(true);
        setShooterSpeed(1800, true);
    }

    public void run() {
        if (ballsFired == 0) {
            if (shooterIsAtTargetSpeed()) {
                extendLoader();
                ballsFired++;
                setShooterSpeed(1800, true);
            }
        }
        if (ballsFired == 1) {
            enableRollers();
            driveToDistance((18.5 * 12.0), 0.4);
            if (atDistance(18.5 * 12.0)) {
                ballsFired++;
            }

        }
        if (ballsFired == 2) {
            disableRollers(); 
            driveToDistance((16 * 12.0), 0.4);
            if (atDistance(16 * 12.0) && shooterIsAtTargetSpeed()) {
                extendLoader();

            }
        }
    }
}
