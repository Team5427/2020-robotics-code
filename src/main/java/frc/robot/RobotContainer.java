/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.MoveShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...
  private SpeedController frontLeft, middleLeft, rearLeft, frontRight, middleRight, rearRight;

  private SpeedControllerGroup left, right;

  private DifferentialDrive driveBase;

  private static DriveTrain driveTrain;

  private Encoder shooterEncoder;

  private WPI_TalonSRX shooterMotor;

  public static Shooter shooter;

  private static Joystick joy;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    frontLeft = new WPI_VictorSPX(0);
    middleLeft = new WPI_VictorSPX(0);
    rearLeft = new WPI_VictorSPX(0);

    frontRight = new WPI_VictorSPX(0);
    middleRight = new WPI_VictorSPX(0);
    rearRight = new WPI_VictorSPX(0);

    left = new SpeedControllerGroup(frontLeft, middleLeft, rearLeft);
    right = new SpeedControllerGroup(frontRight, middleRight, rearRight);

    driveBase = new DifferentialDrive(left, right);
    driveTrain = new DriveTrain(left, right, driveBase);

    shooterMotor = new WPI_TalonSRX(Constants.SHOOTER_MOTOR);
    shooterMotor.configFactoryDefault();

    //shooterEncoder = new Encoder(Constants.SHOOTER_ENC_CHANNEL_A, Constants.SHOOTER_ENC_CHANNEL_B);
    //shooterEncoder.setDistancePerPulse((Math.PI * Constants.SHOOTER_WHEEL_DIAMETER) / Constants.SHOOTER_ENC_PPR);

    shooter = new Shooter(shooterMotor);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    joy = new Joystick(0);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
    // An ExampleCommand will run in autonomous
    return new MoveShooter(0);
  }

  public static DriveTrain getDriveTrain() 
  {
    return driveTrain;
  }

  public static Joystick getJoy()
  {
    return joy;
  }

  public static Shooter getShooter()
  {
    return shooter;
  }

}
