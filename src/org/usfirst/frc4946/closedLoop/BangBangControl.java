/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc4946.closedLoop;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc4946.RateCounter;

/**
 *
 * @author bart
 */
public class BangBangControl extends VelocityControl{
    
    private final DriverStation m_driverStation = DriverStation.getInstance();
    private boolean m_enabled = false;
    
    public BangBangControl(RateCounter counter, SpeedController ctrl) {
        super(counter, ctrl);
        
    }

    public void enable(boolean enable) {
        m_enabled = enable;
        
    }

    public void update() {
        double curSpeed = m_counter.getRPM();
        
        double fullPower = 11.0 / m_driverStation.getBatteryVoltage();
       
        if( m_enabled){
            if (curSpeed < m_targetRPM ) {
                m_speedControl.set(fullPower);

            } else {
                //Consider changing to a partial command, to prevent brakes coming on...
                m_speedControl.set(0);

            }
        }else{
            //If not enabled, stop the motor.
            m_speedControl.set(0);
        
        }
    }
}
