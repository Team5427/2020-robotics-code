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
    public static final double MAX_VELOCITY = 5.0;
    public static final double MAX_ACCELERATION = 5.0;

    // (% Voltage [-1,1])/(Speed in meters per second) [basically we have to measure this]
    public static final double KV = 0;

    public static final double KP = 0;
    public static final double KD = 0;
    public static final double K_THETA = 0;

    public static final double Z_ROT_DAMPENING = 0.75;
    
    public static final double DRIVETRAIN_WHEEL_RADIUS = 0.1524;
    public static final double ENCODER_PPR = 1440;
    public static final double DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_RADIUS * Math.PI / ENCODER_PPR;

    /*CAN Port VALUES*/
	public static final int LEFT_TOP_MOTOR = 10; //? why 10
	public static final int LEFT_MIDDLE_MOTOR = 10;
	public static final int LEFT_BOTTOM_MOTOR = 11;
	public static final int RIGHT_TOP_MOTOR = 6;
	public static final int RIGHT_MIDDLE_MOTOR = 7;
	public static final int RIGHT_BOTTOM_MOTOR = 8;

    /*Encoder Ports */
	public static final int ENCODER_LEFT_PORT_1 = 4;
	public static final int ENCODER_LEFT_PORT_2 = 5; 
	public static final int ENCODER_RIGHT_PORT_1 = 6; 
	public static final int ENCODER_RIGHT_PORT_2 = 7;
	
}
