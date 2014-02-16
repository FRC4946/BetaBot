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
public class AutoMode {

    RobotDrive m_robotDrive;
    Launcher m_launcher;
    Loader m_loader;
    IntakeArm m_intakeArm;
    DistanceSensor m_distanceSensor;

    AutoMode(RobotDrive drive, Launcher launcher, Loader loader, IntakeArm intakeArm, DistanceSensor distanceSensor) {
        m_robotDrive = drive;
        m_launcher = launcher;
        m_loader = loader;
        m_intakeArm = intakeArm;
        m_distanceSensor = distanceSensor;
    }

    public void driveToDistance(double distance, double speed) {
        double currentDistance = m_distanceSensor.getRangeInchs();

        if (currentDistance >= distance && RobotConstants.DISTANCE_SENSOR_RANGE <= Math.abs(currentDistance - distance)) {
            m_robotDrive.drive(speed, 0);
        }
        if (currentDistance <= distance && RobotConstants.DISTANCE_SENSOR_RANGE <= Math.abs(currentDistance - distance)) {
            m_robotDrive.drive(-speed, 0);
        }

    }

    public boolean atDistance(double distance) {
        double currentDistance = m_distanceSensor.getRangeInchs();
        return RobotConstants.DISTANCE_SENSOR_RANGE >= Math.abs(currentDistance - distance);

    }

    public void driveDistance(double distance, double speed) {
        //get current distance
        //subtract input distance from current distance and drive to that distance
        double currentDistance = m_distanceSensor.getRangeInchs();
        driveToDistance(currentDistance - distance, speed);

    }

    public void turnToAngle() {
        //needs work, potentially use the gyro,compass, combo part we have?
    }

    public void startShooter() {
        m_launcher.setOpenLoopEnabled(true);
    }

    public void setShooterSpeed(double speed, boolean mode) {
        if (mode = true) {
            m_launcher.setSpeedRPM(speed);
        }
        if (mode = false) {
            m_launcher.setSpeedOpenLoop(speed);
        }
    }

    public void stopShooter() {
        m_launcher.setOpenLoopEnabled(false);
    }

    public void extendArm() {
        m_intakeArm.setExtended(true);
        m_intakeArm.updateSolenoids();
    }

    public void enableRollers() {
        m_intakeArm.setEnabledRollers(true);
    }

    public void disableRollers() {
        m_intakeArm.setEnabledRollers(false);
    }

    public void extendLoader() {
        m_loader.setExtended(true);
        m_loader.updateSolenoids();
    }

    public void retractLoader() {
        m_loader.setExtended(false);
        m_loader.updateSolenoids();
    }

    public double getCurrentShooterSpeed() {
        //return the speed
        double rpm = m_launcher.getSpeedRPM();
        return rpm;
    }
}
