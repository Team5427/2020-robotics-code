/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //We need to measure these too
    public static final double MAX_VELOCITY = 3.3;
    public static final double MAX_ACCELERATION = 3.21333863;

    // (% Voltage [-1,1])/(Speed in meters per second) [basically we have to measure this]
    public static final double KV = .3030303;

    
    //influence of acceleration on velocity, just a bias which can be further tested.
    public static final double KA = 0.066;

    
    public static final double KP_left = 0.081;
    public static final double KI_left = 0.001352;
    public static final double KD_left = 0.28;
    public static final double KP_right = 0.081;
    public static final double KI_right = 0.001352;
    public static final double KD_right = 0.28;
    public static final double K_THETA_P = 0.002336;
    public static final double K_THETA_D = 0;

    public static final double Z_ROT_DAMPENING = 0.75;

    public static final double TURN_TOLERANCE =  2;
    
    /**
     * Diameter of the drive wheel in meters. AKA 6 inches.
     */
    public static final double DRIVETRAIN_WHEEL_DIAMETER = .1524;
    //public static final double ENCODER_PPR = 691.907;
    public static final double DISTANCE_PER_PULSE = Math.PI * DRIVETRAIN_WHEEL_DIAMETER/360;

    /*CAN Port VALUES*/
	public static final int LEFT_TOP_MOTOR = 10; //? why 10
	public static final int LEFT_MIDDLE_MOTOR = 10;
	public static final int LEFT_BOTTOM_MOTOR = 11;
	public static final int RIGHT_TOP_MOTOR = 6;
	public static final int RIGHT_MIDDLE_MOTOR = 7;
    public static final int RIGHT_BOTTOM_MOTOR = 8;
    
    public static final int INTAKE_MOTOR = 0; //need to configure
    public static final int TRANSPORT_MOTOR = 0;
    public static final int PULLEY_MOTOR = 0;

    /*Encoder Ports */
	public static final int ENCODER_LEFT_PORT_1 = 3;
	public static final int ENCODER_LEFT_PORT_2 = 4;
	public static final int ENCODER_RIGHT_PORT_1 = 6; 
    public static final int ENCODER_RIGHT_PORT_2 = 7;
    
    public static final int INTAKE_PROXIMITY_SENSOR_PORT = 1;
    public static final int TRANSPORT_PROXIMITY_ONE_SENSOR_PORT = 1;
    public static final int TRANSPORT_PROXIMITY_TWO_SENSOR_PORT = 1;
    public static final int PULLEY_PROXIMITY_SENSOR_PORT = 1;

    public static final int INTAKE_PROXIMITY_DIFFERENCE = 8; //arbitrary number for now

    //Joystick buttons
    public static final int INTAKE_BUTTON = 0;
    public static final int TRANSPORT_BUTTON = 0;
    public static final int PULLEY_BUTTON = 0;


    //Speeds
    public static final double INTAKE_TELEOP_SPEED = 0;
    public static final double TRANSPORT_TELEOP_SPEED = 0;
    public static final double PULLEY_TELEOP_SPEED = 0;

    public static final double INTAKE_INTEGRATED_SPEED = 0;
    public static final double TRANSPORT_INTEGRATED_SPEED = 0;
    /*****************Motor ports*****************/

    /**
     * CAN port for shooter motor.
     */
    public static final int SHOOTER_MOTOR = 1;

    /*******************Sensors*******************/

    /**
     * Distance per pulse of encoder on shooter motor.
     */
    public static final double SHOOTER_ENC_DIST_PER_PULSE = 0;

    /**
     * Channel 1 value of the shooter motor encoder. 
     */
    public static final int SHOOTER_ENC_CHANNEL_A = 1;

    /**
     * Channel 2 value of the shooter motor encoder.
     */
    public static final int SHOOTER_ENC_CHANNEL_B = 2;

    /**
     * Pulses per revolution of the shooter motor encoder.
     */
    public static final int SHOOTER_ENC_PPR = 1440;

    /******************Dimensions*****************/

    /**
     * Wheel diameter of the shooter (in inches).
     */
    public static final double SHOOTER_WHEEL_DIAMETER = .1524; //test data


    /******************* PID *********************/

    /**
     * Proportional gain value for shooter PID controller.
     */
    public static final double kP_SHOOTER = 1.2;

    /**
     * Integral gain value for shooter PID controller.
     */
    public static final double kI_SHOOTER = 0;

    /**
     * Derivative gain value for shooter PID controller.
     */
    public static final double kD_SHOOTER = 0;

    /**
     * Feedforward constant for the shooter PID controller with TalonSRX.
     */
    public static final double kF_SHOOTER = 2.407;

    /**
     * The period between shooter controller updates in seconds.
     */
    public static final double SHOOTER_PERIOD = 0.5;

    /**
     * Maximum rotations per second of the shooter.
     */
    public static final double SHOOTER_TOLERANCE = 4.0;

    /**
     * Desired rotations per second of the shooter.
     */
    public static final double TARGET_SHOOTER_RPM = 4.0;

    /**
     * Static gain value for feedforward control with the shooter motor.
     */
    public static final double kS_SHOOTER = 0;

    /**
     * Velocity gain value for feedforward control with the shooter motor.
     */
    public static final double kV_SHOOTER = 0;

    public static final double SHOOTER_SETPOINT = 0;

    public static final int K_TIMEOUT_MS = 0;

    public static final int SHOOTER_PID_ID = 0;
}
