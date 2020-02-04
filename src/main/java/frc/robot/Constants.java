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
    public static final double kP_SHOOTER = 1.0;

    /**
     * Integral gain value for shooter PID controller.
     */
    public static final double kI_SHOOTER = 1.0;

    /**
     * Derivative gain value for shooter PID controller.
     */
    public static final double kD_SHOOTER = 1.0;

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
