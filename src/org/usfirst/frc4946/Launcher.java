package org.usfirst.frc4946;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc4946.closedLoop.BangBangControl;
import org.usfirst.frc4946.closedLoop.PIDControl;
import org.usfirst.frc4946.closedLoop.VelocityControl;

public class Launcher {

    //Motor contorl
    private final Talon m_launcherTopController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_TOP);
    private final Talon m_launcherBottomController = new Talon(RobotConstants.PWM_MOTOR_LAUNCHER_BOTTOM);

    //Hall sensors
    private final RateCounter m_launcherTopCounter = new RateCounter(RobotConstants.HALL_SENSOR_TOP);
    private final RateCounter m_launcherBottomCounter = new RateCounter(RobotConstants.HALL_SENSOR_BOT);

    //-------------------------------
    VelocityControl m_topVelocity = new BangBangControl(m_launcherTopCounter, m_launcherTopController);
    VelocityControl m_bottomVelocity = new BangBangControl(m_launcherBottomCounter, m_launcherBottomController);
         
    //Alternate control
    //VelocityControl m_topVelocity = new PIDControl(m_launcherTopCounter, m_launcherTopController);
    //VelocityControl m_bottomVelocity = new PIDControl(m_launcherBottomCounter, m_launcherBottomController);
    
    //-------------------------------
    //Open loop control
    private double speedOpenLoop = 0.0;
    private boolean motorsAreEnabled = false;
    
    //-------------------------------
    private final DriverStation m_driverStation = DriverStation.getInstance();
    DriverStationLCD m_driverStationLCD = DriverStationLCD.getInstance();

    //-------------------------------
    private boolean isClosedLoopControl = false;

    Launcher() {
        LiveWindow.addActuator("Shooter", "Top motor", m_launcherTopController);
        LiveWindow.addActuator("Shooter", "Bottom motor", m_launcherBottomController);
        
        m_launcherBottomCounter.start();
        m_launcherTopCounter.start();
        m_bottomVelocity.setIsReversed(true);

    }
    
    public void update() {

        if (isClosedLoopControl) {

            m_topVelocity.update();
            m_bottomVelocity.update();
            
            double topDifference = m_launcherTopCounter.getRPM() - m_topVelocity.getTargetSpeed();
            double bottomDifference = m_launcherBottomCounter.getRPM() - m_topVelocity.getTargetSpeed();
            
            m_driverStationLCD.println(RobotConstants.LCD_LAUNCHER, 1,
                    "TCmd:" + m_topVelocity.getTargetSpeed() 
                    + "T:" + (int) topDifference
                    + "|B:" + (int) bottomDifference
                    + "                         ");

        } else {
            m_driverStationLCD.println(RobotConstants.LCD_LAUNCHER, 1,
                    "T:" + (int) m_launcherTopCounter.getRPM()
                    + "|B:" + (int) m_launcherBottomCounter.getRPM()
                    + "                         ");

        }
    }

    public void toggleEnabled() {
        motorsAreEnabled = !motorsAreEnabled;
        if (isClosedLoopControl) {
            setClosedLoopEnabled(motorsAreEnabled);
            
        } else {
            setOpenLoopEnabled(motorsAreEnabled);
            
        }

    }

    public void setClosedLoopEnabled(boolean isEnabled) {
        isClosedLoopControl = isEnabled;
        motorsAreEnabled = isEnabled;

        //Enable the velocity controllers
        m_topVelocity.enable(isEnabled);
        m_bottomVelocity.enable(isEnabled);
        
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
            m_launcherBottomController.set(speedOpenLoop*-1);
            
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
        
        m_topVelocity.setTargetRPM(rpm);
        m_bottomVelocity.setTargetRPM(rpm);
        
    }

    public double getSpeedRPM() {
        return m_launcherTopCounter.getRPM();
        
    }

    public boolean isClosedLoopControl() {
        return isClosedLoopControl;

    }
}

