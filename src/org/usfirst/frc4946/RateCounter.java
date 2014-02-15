/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4946;
 
/**
 *
 * @author Stefan
 */
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDSource;

public class RateCounter extends Counter implements PIDSource {

    public RateCounter(int channel) {
        super(channel);
        setMaxPeriod(10);
    }

    /**
     * Returns the time between the last two ticks, as a raw RPM counter.
     */
    public double getRPM() {
        double period = getPeriod();
        double rate = 0;
        
        if( period < 10 ) // At least 60rpm
            rate = 1.0 / period * 60.0;
        
        return rate;

    }

    public double pidGet() {
        return getRPM();

    }

}
