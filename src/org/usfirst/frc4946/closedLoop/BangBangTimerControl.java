/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4946.closedLoop;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.TimerTask;
import org.usfirst.frc4946.RateCounter;

/**
 *
 * @author
 */
public class BangBangTimerControl extends VelocityControl {

    private final DriverStation m_driverStation = DriverStation.getInstance();
    private boolean m_enabled = false;

    private final long m_periodMSec = 5;
    java.util.Timer m_controlLoop;

    public BangBangTimerControl(RateCounter counter, SpeedController ctrl) {
        super(counter, ctrl);

        m_controlLoop = new java.util.Timer();
        m_controlLoop.schedule(new BangBangTimerControl.BangBangTask(this), 0L, m_periodMSec);

    }

    /**
     * Free the PID object
     */
    public void free() {
        m_controlLoop.cancel();
        m_controlLoop = null;
    }

    private class BangBangTask extends TimerTask {

        private BangBangTimerControl m_controller;

        public BangBangTask(BangBangTimerControl controller) {
            if (controller == null) {
                throw new NullPointerException("Given BangBangTimerControl was null");
            }
            m_controller = controller;
        }

        public void run() {
            m_controller.timerUpdate();

        }
    }

    public void enable(boolean enable) {
        m_enabled = enable;

    }

    public void update() {
        //Handled by the timer
    }

    public void timerUpdate() {

        synchronized (this) {
            double curSpeed = m_counter.getRPM();

            double fullPower = 12.0 / m_driverStation.getBatteryVoltage();

            if (m_isReversed) {
                fullPower *= -1;

            }

            if (m_enabled) {
                if (curSpeed < m_targetRPM) {
                    m_speedControl.set(fullPower);

                } else {
                    m_speedControl.set(0);

                }
            } else {
                //If not enabled, stop the motor.
                m_speedControl.set(0);

            }
        }
    }
}
