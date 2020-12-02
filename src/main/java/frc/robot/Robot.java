/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MoveStraightPID;
import frc.robot.commands.PointTurn;
import frc.robot.commands.StraightAndTurn;
import frc.robot.commands.MoveStraight;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Pulley;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import com.revrobotics.ColorSensorV3;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private static double proximityVoltage;
  PowerDistributionPanel pdp = new PowerDistributionPanel(16);

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private String gameData;
  //competition code

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // SmartDashboard.putNumber("Average velocity", RobotContainer.getDriveTrain().getAvgRate());
    // SmartDashboard.putNumber("Average LEFT VELOCITY", RobotContainer.getEncLeft().getRate());
    // SmartDashboard.putNumber("Average RIGHT VELOCITY", RobotContainer.getEncRight().getRate());
    // SmartDashboard.putNumber("AHRS X Speed", RobotContainer.getAHRS().getVelocityX());
    // SmartDashboard.putNumber("AHRS Y Speed", RobotContainer.getAHRS().getVelocityY());
    // SmartDashboard.putNumber("NavX", RobotContainer.getAHRS().getAngle());
    // SmartDashboard.putNumber("Left Encoder Distance", RobotContainer.getEncLeft().getDistance());
    // SmartDashboard.putNumber("Right Encoder Distance", RobotContainer.getEncRight().getDistance());
    // SmartDashboard.putNumber("Average Distance", RobotContainer.getDriveTrain().getAvgDistance());
    // SmartDashboard.putNumber("Velocity", RobotContainer.getDriveTrain().getAvgRate());
    SmartDashboard.putNumber("Proximity one", RobotContainer.getTransport().getDistance());
    SmartDashboard.putNumber("Proximity two", RobotContainer.getTransport().getDistanceTwo());
    SmartDashboard.putNumber("Proximity three", RobotContainer.getPulley().getDistance());
    SmartDashboard.putNumber("Balls In", RobotContainer.ballsIn);
    SmartDashboard.putNumber("Balls Out", RobotContainer.ballsOut);
    SmartDashboard.putNumber("Left encoder", RobotContainer.getElevator().getLeftEnc().getRaw());
    SmartDashboard.putNumber("Right encoder", RobotContainer.getElevator().getRightEnc().getRaw());
    //System.out.println(pdp.getCurrent(2) + ": current :" + pdp.getCurrent(13));


    SmartDashboard.putNumber("Ultrasonic", RobotContainer.getUltrasonic().getRangeInches());

    // m_robotContainer.getColorSensor().getProximity();
    // m_robotContainer.getColorSensor().getColor();

    // gameData = DriverStation.getInstance().getGameSpecificMessage();

    // if(gameData.length() > 0)
    // {
    //   switch(gameData.charAt(0))
    //   {
    //     case 'B':
    //       SmartDashboard.putString("Received: ", "Blue");
    //       SmartDashboard.putString("Go To: ", "Red");
    //       m_robotContainer.color = 'R';
    //       break;
    //     case 'Y':
    //       SmartDashboard.putString("Received: ", "Yellow");
    //       SmartDashboard.putString("Go To: ", "Green");
    //       m_robotContainer.color = 'G';
    //       break;
    //     case 'R':
    //       SmartDashboard.putString("Received: ", "Red");
    //       SmartDashboard.putString("Go To: ", "Blue");
    //       m_robotContainer.color = 'B';
    //       break;
    //     case 'G':
    //       SmartDashboard.putString("Received: ", "Green");
    //       SmartDashboard.putString("Go To: ", "Yellow");
    //       m_robotContainer.color = 'Y';
    //       break;
    //     default:
    //       SmartDashboard.putString("Error", "Corrupted data");
    //       break;
    //   }
    //   m_robotContainer.getColorSensor().getColor();
    // }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() 
  {
    // RobotContainer.getAHRS().reset();
    // RobotContainer.getEncLeft().reset();
    // RobotContainer.getEncRight().reset();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // RobotContainer.getShooter().getShooterMotorTop().set(0.4);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
}