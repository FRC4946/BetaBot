package org.usfirst.frc4946.closedLoop;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDSource;

public class RateCounter extends Counter implements PIDSource {
	
	public RateCounter(int channel) {
		super(channel);
		
	}
	
	/**
	 * Returns the time between the last two ticks, as a raw RPM counter.
	 */
	
	public double getRPM(){
		double rate = 1.0 / getPeriod() * 60.0;
		return rate;
		
	}
	
    public double pidGet(){
    	return getRPM();
    	
    }

}
