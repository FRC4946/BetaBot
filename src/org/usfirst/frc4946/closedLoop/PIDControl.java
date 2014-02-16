/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc4946.closedLoop;

import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc4946.RateCounter;
import org.usfirst.frc4946.RobotConstants;

/**
 *
 * @author bart
 */
public class PIDControl extends VelocityControl{
    //PID parameters
    private final double m_KP = 0.2; // error of 100RPM -> 0.1V change for command?
    private final double m_KD = 0;
    private final double m_ClosedLoopTolerance = 2; //percent
    
    //The modified rate controller
    PIDRateController m_pid = new PIDRateController(m_KP, 0, m_KD, m_counter, m_speedControl);
    
    public PIDControl(RateCounter counter, SpeedController ctrl) {
        super(counter, ctrl);
        
        //Don't allow more than MAX RPM.
        m_pid.setInputRange(0,RobotConstants.SHOOTER_MAX_RPM);
        m_pid.setTolerance(m_ClosedLoopTolerance);
        
    }

    public void enable(boolean enable) {
        if(enable){
            m_pid.enable();

        }else{
            m_pid.disable();

        }
    }

    public void update() {
        //Do nothing
        
    }
 
    public void setTargetRPM(double rpm){
        super.setTargetRPM(rpm);
        m_pid.setSetpoint(rpm);
        
    }
}
