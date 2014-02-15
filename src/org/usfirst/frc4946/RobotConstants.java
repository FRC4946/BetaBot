package org.usfirst.frc4946;

public class RobotConstants {

    //*** Motors ***\\
    final static int PWM_MOTOR_LEFT_FRONT = 1;
    final static int PWM_MOTOR_LEFT_REAR = 2;
    final static int PWM_MOTOR_RIGHT_FRONT = 3;
    final static int PWM_MOTOR_RIGHT_REAR = 4;

    final static int PWM_MOTOR_LAUNCHER_TOP = 7;
    final static int PWM_MOTOR_LAUNCHER_BOTTOM = 6;

    final static int PWM_MOTOR_INTAKE = 5;

    //*** Joysticks ***\\
    public static final int JOYSTICK_LEFT = 1;
    public static final int JOYSTICK_RIGHT = 2;

    //*** Pneumatics ***\\
    public static final int COMPRESSOR_PRESSURE_SWITCH = 1; // On the GPIO header of the digital sidecar
    public static final int COMPRESSOR_RELAY = 1;			// On the relay header of the digital sidecar

    public static final int EXTEND_ARM_VALVE_RELAY = 1; //The valve that extends the pistons on the intake arm
    public static final int RETRACT_ARM_VALVE_RELAY = 2; //The valve that retracts the pistons on the intake arm

    public static final int BALL_LIFT_VALVE_RELAY = 3; // The valve to raise the ball lifter. (Purple in the diagram)
    public static final int BALL_LOWER_VALVE_RELAY = 4; // The valve to lower the ball lifter

    //*** Buttons ***\\
    public static int JOYSTICK_BUTTON_INTAKE_OUT = 6;
    public static int JOYSTICK_BUTTON_INTAKE_IN = 7;
    public static int JOYSTICK_BUTTON_LAUNCHER_ON = 11;
    public static int JOYSTICK_BUTTON_LAUNCHER_OFF = 10;
    public static int JOYSTICK_BUTTON_INTAKE_ROLLER = 2;
    public static int JOYSTICK_BUTTON_SHOOT_ORIENTATION = 5;
    public static int JOYSTICK_BUTTON_INTAKE_ORIENTATION = 6;
    public static int JOYSTICK_BUTTON_RPM_MODE = 8;
    public static int JOYSTICK_BUTTON_VOLTAGE_MODE = 9;

    //*** Misc ***\\
    public static int SOLENOID_COOLDOWN_TIME = 1000;
    public static int CONSOLE_UPDATE_TIME = 10;
    public static int DISTANCE_SENSOR = 2;
    public static int HALL_SENSOR_TOP=4;
    public static int HALL_SENSOR_BOT=3;
    public static int SHOOTER_MAX_VOLTAGE=7;
    public static int SHOOTER_MAX_RPM=5000;
    public static int DISTANCE_SENSOR_RANGE=5;
}
