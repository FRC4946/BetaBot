/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc4946.closedLoop;

import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc4946.RateCounter;

/**
 *
 * @author bart
 */
public abstract class VelocityControl {
    
    public final RateCounter m_counter;
    
    public final SpeedController m_speedControl;
    
    protected double m_targetRPM;

    abstract public void enable(boolean enable);
    
    abstract public void update();
    protected boolean m_isReversed = false;
    
    public void setIsReversed(boolean reversed){
        
        m_isReversed= reversed;
        
    }
    public VelocityControl( RateCounter counter, SpeedController ctrl){
        m_counter = counter;
        m_speedControl = ctrl;    
        
    }

    public double getTargetSpeed() {
        return m_targetRPM;
    }

    public void setTargetRPM(double speed) {
        this.m_targetRPM = speed;
        
    }
        
    public double getCurrentRPM(){
        return m_counter.getRPM();
        
    }
}
