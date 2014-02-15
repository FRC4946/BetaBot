package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Launcher {

    private final Talon m_launcherTopController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
    private final Talon m_launcherBottomController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);
    private final DriverStation m_driverStation;
    DriverStationLCD m_driverStationLCD = DriverStationLCD.getInstance();
    private final RateCounter m_launcherTopCounter = new RateCounter(RobotConstants.HALL_SENSOR_TOP);
    private final RateCounter m_launcherBottomCounter = new RateCounter(RobotConstants.HALL_SENSOR_BOT);
    
    private double speed = 0.0;
    private boolean motorsAreEnabled = false;

    Launcher() {
        LiveWindow.addActuator("Shooter", "Top motor", m_launcherTopController);
        LiveWindow.addActuator("Shooter", "Bottom motor", m_launcherBottomController);

        m_launcherBottomCounter.start();
        m_launcherTopCounter.start();
        
        //LiveWindow.addActuator("Shooter", "Top counter", m_launcherTopCounter);
        //LiveWindow.addActuator("Shooter", "Bottom Counter", m_launcherBottomCounter);

        m_driverStation = DriverStation.getInstance();

    }

    
    public void update(){
       m_driverStationLCD.println(DriverStationLCD.Line.kMain6, 1,
               "T:" + (int) m_launcherTopCounter.getRPM()
               + " |B:" + (int) m_launcherBottomCounter.getRPM());
       
    }
    
    public void toggleEnabled() {
        motorsAreEnabled = !motorsAreEnabled;

        setEnabled(motorsAreEnabled);
    }

    /**
     * Start or stop the motors. If the speed has been changed, call
     * setEnabled(true) to update the motors.
     *
     * @param isEnabled Whether to enable the motors or not.
     */
    public void setEnabled(boolean isEnabled) {

        motorsAreEnabled = isEnabled;

        if (isEnabled) {
            m_launcherTopController.set(speed);
            m_launcherBottomController.set(speed * -1);
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

        speed = power / m_driverStation.getBatteryVoltage();

        // Old method, using just a percentage
        //if(power >= -1.0 && power <= 1.0){
        //  speed = power;
        //}
    }

    public void setSpeedRPM(double rpm) {

    }
    
}
