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

    //AutoMode autoRoutine = new AutoMode(m_robotDrive, m_launcher, m_loader, m_intakeArm, m_distanceSensor);
    int step = 0;
    int counter = 0;

    public AutoMoveAndShoot(RobotDrive drive, Launcher launcher, Loader loader, IntakeArm intakeArm, DistanceSensor distanceSensor) {
        super(drive, launcher, loader, intakeArm, distanceSensor);
    }

    public void init() {
        step = 0;
        counter = 0;
        atDistanceCount = 0;

        extendArm();
        m_driverStation.println(RobotConstants.AUTO_LCD_INTAKE, 1, "EXTENDED ARM");
        m_driverStation.updateLCD();
        startShooter(true);
        setShooterSpeed(1425, true);
        m_driverStation.println(RobotConstants.AUTO_LCD_LAUNCHER, 1, "SHOOTER ON 1800");
    }
    int atDistanceCount = 0;

    public void run() {
        m_launcher.update();
        counter++;
        m_driverStation.println(RobotConstants.AUTO_LCD_INTAKE, 1, "Dist " + m_distanceSensor.getRangeInchs());
        if (step == 0 && counter > 400) {
            driveToDistance(10 * 12, 0.35);

        }

        if (atDistance(10 * 12) && step == 0) {
            atDistanceCount++;
            if (atDistanceCount > 2) {
                drive(0.0, 0.0);
            }
        } else {
            atDistanceCount = 0;

        }

        if (atDistanceCount > 10 && step == 0) {
            step = 1;
            drive(0.0, 0.0);
            counter = 0;
            m_driverStation.println(RobotConstants.AUTO_LCD_LAUNCHER, 1, "AT DIS & SPEED   ");
        }

        if (step == 1 && counter > 100) {

            extendLoader();
            m_driverStation.println(RobotConstants.AUTO_LCD_LOADER, 1, "SHOOTING");
        }
    }

}
