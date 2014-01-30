package org.usfirst.frc4946;

public class RobotConstants {
        final static int PWM_MOTOR_LEFT_FRONT = 1;
        final static int PWM_MOTOR_LEFT_REAR = 2;
        final static int PWM_MOTOR_RIGHT_FRONT = 3;
        final static int PWM_MOTOR_RIGHT_REAR = 4;
        
        
        public static final int JOYSTICK_LEFT = 1;
        public static final int JOYSTICK_RIGHT = 2;
        
        public static final double DRIVE_JOYSTICK_DEADZONE = 0.1;
        public static final double TASK_JOYSTICK_DEADZONE = 0.1;
        
        public static final int PWM_LEFT_SERVO = 9;
        public static final int PWM_RIGHT_SERVO = 10;
        
        
        //*** Hypothetical ***\\
        
        public static final int COMPRESSOR_PRESSURE_SWITCH = 1; // On the GPIO header of the digital sidecar
        public static final int COMPRESSOR_RELAY = 1;			// On the relay header of the digital sidecar
        
        public static final int BALL_LIFT_VALVE_RELAY = 2; // The valve for the ball lifter. (Purple in the diagram)
        
        public static final int EXTEND_ARM_VALVE_RELAY = 3; //The valve that extends the pistons on the intake arm
        public static final int RETRACT_ARM_VALVE_RELAY = 4; //The valve that retracts the pistons on the intake arm
        
}