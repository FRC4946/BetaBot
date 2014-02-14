/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.AnalogChannel;


/**
 *
 * @author Stefan
 */
public class DistanceSensor {
    AnalogChannel  m_distanceSensor =  new AnalogChannel (1);
    double range=0;
    double volt=0;
    public DistanceSensor(){
        
    }
    public void turnOn(){
       
    }
    public double getRangeInchs(){
        
    volt = m_distanceSensor.getVoltage();
    range = volt/0.009766;
    return range;
    }
}
