/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.math.BigDecimal;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //****** MOTION PROFILING / DRIVE TRAIN *******/
    //We need to measure these too
    public static final double MAX_VELOCITY = 1.6333;
    public static final double MAX_ACCELERATION = 1.0611;

    // (% Voltage [-1,1])/(Speed in meters per second) [basically we have to measure this]
    public static final double KV = 1/MAX_VELOCITY;

    //influence of acceleration on velocity, just a bias which can be further tested.
    public static final double KA = 0;

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
    public static final double DRIVE_TOLERANCE = 0.5;
    public static final double DRIVETRAIN_WHEEL_DIAMETER = .1524;
    public static final double DISTANCE_PER_PULSE = (Math.PI * DRIVETRAIN_WHEEL_DIAMETER/1440)/3.68;

    public static final double INTAKE_PROXIMITY_DIFFERENCE = 2.0;

    public static final double PROXIMITY_COVERED = 2.7;
    public static final double PROXIMITY_UNCOVERED = 3.7;

    public static final int FIRST_PROXIMITY_UNCOVERED = 0;

    public static final int PROXIMITY_TOLERANCE = 0;

    //Joystick buttons

    public static final int INTAKE_BUTTON = 2;
    public static final int TRANSPORT_BUTTON = 7;
    public static final int PULLEY_BUTTON = 8;
    public static final int SHOOTER_BUTTON = 11;
    
    public static final int ROTATION_CONTROL = 0;
    public static final int POSITION_CONTROL = 0;

    public static final int COLOR_BUTTON = 0;
    public static final int TILT_BUTTON_UP = 6;
    public static final int TILT_BUTTON_DOWN = 4;

    public static final int ELEVATOR_UP_BUTTON = 5;
    public static final int ELEVATOR_DOWN_BUTTON = 3;

    public static final int SHOOTER_TELEOP = 1;



    //Speeds
    public static final double INTAKE_TELEOP_SPEED = 0.7;
    public static final double TRANSPORT_TELEOP_SPEED = -1.0;
    public static final double PULLEY_TELEOP_SPEED = 1.0;
    public static final double INTAKE_INTEGRATED_SPEED = 0.9;
    public static final double TRANSPORT_INTEGRATED_SPEED = 0.9;
    public static final double ELEVATOR_SPEED = 0.6;
    public static final double TILT_SPEED = 1.0;
    public static final double SHOOTER_DOWN_SPEED = 0.9;
    public static final double SHOOTER_UP_SPEED = 0.9;
    public static final double TRANSPORT_SHOOTING_SPEED = 0.7;
    public static final double PULLEY_SHOOTING_SPEED = 0.7;

    public static final double TILT_UP_TIMEOUT = 0.5;
    //1106.0 :: 1037.25


    /*****************Motor ports*****************/

    public static final int LEFT_TOP_MOTOR = 14; 
	public static final int LEFT_BOTTOM_MOTOR = 15;
	public static final int RIGHT_TOP_MOTOR = 0;
    public static final int RIGHT_BOTTOM_MOTOR = 1;
    
    public static final int INTAKE_MOTOR = 10; 
    public static final int TRANSPORT_MOTOR = 12;
    public static final int PULLEY_MOTOR = 11;

    public static final int COLOR_WHEEL_MOTOR = 0;

    public static final int TILT_MOTOR = 3;

    public static final int ELEVATOR_LEFT_MOTOR = 13;
    public static final int ELEVATOR_RIGHT_MOTOR = 2;

    public static final int SHOOTER_MOTOR_TOP = 9;
    public static final int SHOOTER_MOTOR_BOTTOM = 6;

    public static final int CLIMB_MANIPULATOR = 4;

    /*******************Sensors*******************/

    public static final int ENCODER_LEFT_PORT_1 = 6;
	public static final int ENCODER_LEFT_PORT_2 = 7;
	public static final int ENCODER_RIGHT_PORT_1 = 4; 
    public static final int ENCODER_RIGHT_PORT_2 = 5;

    public static final int ELEVATOR_LEFT_PORT_1 = 0;
    public static final int ELEVATOR_LEFT_PORT_2 = 1;
    public static final int ELEVATOR_RIGHT_PORT_1 = 2;
    public static final int ELEVATOR_RIGHT_PORT_2 = 3;

    public static final int ELEVATOR_LIMIT_RIGHT = 8;
    public static final int ELEVATOR_LIMIT_LEFT = 9;

    public static final int LIMIT_SWITCH_TILT = 21;
    
    public static final int TRANSPORT_PROXIMITY_ONE_SENSOR_PORT = 0;
    public static final int TRANSPORT_PROXIMITY_TWO_SENSOR_PORT = 2;
    public static final int PULLEY_PROXIMITY_SENSOR_PORT = 1;

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

    public static final double kP_SHOOTER_BOTTOM = 1.2;

    public static final double kI_SHOOTER_BOTTOM = 0;

    public static final double kD_SHOOTER_BOTTOM = 0;

    public static final double kF_SHOOTER_BOTTOM = 2.407;

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

    public static final double SHOOTER_ERROR_TOLERANCE = 0.5;
    
    /* Color Targets */
    public static final Color kBlueTarget = ColorMatch.makeColor(0.11, 0.42, 0.47);
    public static final Color kGreenTarget = ColorMatch.makeColor(0.16, 0.57, 0.26);
    public static final Color kRedTarget = ColorMatch.makeColor(0.53, 0.33, 0.12);
    public static final Color kYellowTarget = ColorMatch.makeColor(0.31, 0.56, 0.12);

    public static final BigDecimal rBlueTarget = new BigDecimal("0.11");
    public static final BigDecimal gBlueTarget = new BigDecimal("0.42");
    public static final BigDecimal bBlueTarget = new BigDecimal("0.47");

    public static final BigDecimal rGreenTarget = new BigDecimal("0.16");
    public static final BigDecimal gGreenTarget = new BigDecimal("0.57");
    public static final BigDecimal bGreenTarget = new BigDecimal("0.26");

    public static final BigDecimal rRedTarget = new BigDecimal("0.53");
    public static final BigDecimal gRedTarget = new BigDecimal("0.33");
    public static final BigDecimal bRedTarget = new BigDecimal("0.12");

    public static final BigDecimal rYellowTarget = new BigDecimal("0.31");
    public static final BigDecimal gYellowTarget = new BigDecimal("0.56");
    public static final BigDecimal bYellowTarget = new BigDecimal("0.12");

    public static final double COLOR_THRESHOLD = 0.05;

    public static final double COLOR_WHEEL_SPEED = 0.2;

    public static final double ENC_TOLERANCE = 0.5;

    public static final double ELEVATOR_UPPER_LIMIT = 15;
}
