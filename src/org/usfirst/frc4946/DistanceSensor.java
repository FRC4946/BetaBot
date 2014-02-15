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
    AnalogChannel  m_distanceSensor =  new AnalogChannel (RobotConstants.DISTANCE_SENSOR);
    double range=0;
    double voltage=0;
    public DistanceSensor(){
        
    }
    public void turnOn(){
       
    }
    public double getRangeInchs(){
        
    voltage = m_distanceSensor.getVoltage();
    //at 5v it's 4.9 millivolts per 2cm
    //so 4.9/2 = 2.45 mv per 1 cm
    // 2.45mv = 0.00245v per cm
    // we want inches cause we know field size in inches (1 inch = 2.54 cm
    // 2.54*0.00245=0.006223
    //value I found on chief delphi, sensor might not return 0 volt so we might need to add a value to the calculated one above
    range = voltage/0.006223;
    return range;
    }
}
