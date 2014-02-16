package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Launcher {

    private final Talon m_launcherTopController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
    private final Talon m_launcherBottomController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);

    private final RateCounter m_launcherTopCounter = new RateCounter(RobotConstants.HALL_SENSOR_TOP);
    private final RateCounter m_launcherBottomCounter = new RateCounter(RobotConstants.HALL_SENSOR_BOT);

    private final DriverStation m_driverStation;
    DriverStationLCD m_driverStationLCD = DriverStationLCD.getInstance();
    
    private double speedOpenLoop = 0.0;
    private double speedClosedLoop = 0;
    
    private boolean motorsAreEnabled = false;

    private boolean isClosedLoopControl = false;
    
    Launcher() {
        LiveWindow.addActuator("Shooter", "Top motor", m_launcherTopController);
        LiveWindow.addActuator("Shooter", "Bottom motor", m_launcherBottomController);

        m_launcherBottomCounter.start();
        m_launcherTopCounter.start();
        
        m_driverStation = DriverStation.getInstance();

    }

    
    public void update(){
       
       if( isClosedLoopControl && motorsAreEnabled){
          
           double bottomSpeed = m_launcherBottomCounter.getRPM();
           if( bottomSpeed < speedClosedLoop )
               m_launcherBottomController.set(-1);
           else
               m_launcherBottomController.set(0);
           
           double topSpeed = m_launcherTopCounter.getRPM();
           if( topSpeed < speedClosedLoop )
               m_launcherTopController.set(1);
           else
               m_launcherTopController.set(0);
           
           String top = topSpeed < speedClosedLoop ? "|T:": "|t:" ;
           String bottom = bottomSpeed < speedClosedLoop ? "|B:": "|b:" ;
           
           m_driverStationLCD.println(RobotConstants.LCD_LAUNCHER, 1,
            (int) speedClosedLoop 
            + top + (int) m_launcherTopCounter.getRPM()
            + bottom + (int) m_launcherBottomCounter.getRPM());
       
       }else{ 
            m_driverStationLCD.println(RobotConstants.LCD_LAUNCHER, 1,
               "|T:" + (int) m_launcherTopCounter.getRPM()
               + "|B:" + (int) m_launcherBottomCounter.getRPM());
       }
    }
    
    public void toggleEnabled() {
        motorsAreEnabled = !motorsAreEnabled;
        if( isClosedLoopControl)
            setClosedLoopEnabled(motorsAreEnabled);
        else
            setOpenLoopEnabled(motorsAreEnabled);
        
    }

    public void setClosedLoopEnabled(boolean isEnabled){
        isClosedLoopControl = isEnabled;
        motorsAreEnabled = isEnabled;

    } 
    
    /**
     * Start or stop the motors. If the speed has been changed, call
     * setEnabled(true) to update the motors.
     *
     * @param isEnabled Whether to enable the motors or not.
     */
    public void setOpenLoopEnabled(boolean isEnabled) {
        isClosedLoopControl = false;
        motorsAreEnabled = isEnabled;

        if (isEnabled) {
            m_launcherTopController.set(speedOpenLoop);
            m_launcherBottomController.set(speedOpenLoop * -1);
        } else {
            m_launcherTopController.set(0.0);
            m_launcherBottomController.set(0.0);
        }
    }

    public boolean isEnabled() {
        return motorsAreEnabled;
    }

    
    
    
    /**
     * Set the speed of the motors without checking the result. After calling
     * this, call setEnabled(true) to update the motors.
     *
     * @param power The speed to set. Should be between -12 and 12 ish
     */
    public void setSpeedOpenLoop(double power) {
        isClosedLoopControl = false;
        speedOpenLoop = power / m_driverStation.getBatteryVoltage();

        // Old method, using just a percentage
        //if(power >= -1.0 && power <= 1.0){
        //  speed = power;
        //}
    }

    public void setSpeedRPM(double rpm) {
        speedClosedLoop = rpm;
        
    }
    public double getSpeedRPM() {
        double rpm= m_launcherTopCounter.getRPM();
        return rpm;    
    }

    public boolean isClosedLoopControl() {
            return isClosedLoopControl;
    
    }
}
