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
    //We need to measure these too
    public static final double MAX_VELOCITY = 5.0;
    public static final double MAX_ACCELERATION = 5.0;

    // (% Voltage [-1,1])/(Speed in meters per second) [basically we have to measure this]
    public static final double KV = 0;

    public static final double KP = 0;
    public static final double KD = 0;
    public static final double K_THETA = 0;

    public static final double Z_ROT_DAMPENING = 0.75;
    
    /**
     * Diameter of the drive wheel in meters.
     */
    public static final double DRIVETRAIN_WHEEL_DIAMETER = .1524;
    public static final double ENCODER_PPR = 691.907;
    public static final double DISTANCE_PER_PULSE = 0.0000367;

    /*CAN Port VALUES*/
	public static final int LEFT_TOP_MOTOR = 10; //? why 10
	public static final int LEFT_MIDDLE_MOTOR = 10;
	public static final int LEFT_BOTTOM_MOTOR = 11;
	public static final int RIGHT_TOP_MOTOR = 6;
	public static final int RIGHT_MIDDLE_MOTOR = 7;
	public static final int RIGHT_BOTTOM_MOTOR = 8;

    /*Encoder Ports */
	public static final int ENCODER_LEFT_PORT_1 = 3;
	public static final int ENCODER_LEFT_PORT_2 = 4;
	public static final int ENCODER_RIGHT_PORT_1 = 6;
    public static final int ENCODER_RIGHT_PORT_2 = 7;
    
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
}
