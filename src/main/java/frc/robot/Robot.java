/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Vision.GripPipeline;
import frc.robot.Vision.Target;
import frc.robot.commands.MotionVision;
import frc.robot.commands.MoveStraightPID;
import frc.robot.commands.PointTurn;
import frc.robot.commands.StraightAndTurn;
import frc.robot.commands.VisionMove;
import frc.robot.commands.VisionTurn;
import edu.wpi.first.wpilibj.SPI;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  //created dev branch 
  
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private boolean targetCentered = true;
  private static CameraServer cameraServer;
  private static NetworkTable networkTables;

  private static AHRS ahrs;
  private int status = 0;


  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    ahrs = new AHRS(SPI.Port.kMXP);
    ahrs.setAngleAdjustment(0);
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
    SmartDashboard.putNumber("NavX", RobotContainer.getAHRS().getAngle());
    SmartDashboard.putNumber("Left Encoder Distance", RobotContainer.getEncLeft().getDistance());
    SmartDashboard.putNumber("Right Encoder Distance", RobotContainer.getEncRight().getDistance());
    SmartDashboard.putNumber("Average Distance", RobotContainer.getDriveTrain().getAvgDistance());

    
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
  public void autonomousInit() {
    RobotContainer.getAHRS().reset();
    RobotContainer.getEncLeft().reset();
    RobotContainer.getEncRight().reset();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }

    //CommandScheduler.getInstance().schedule(new StraightAndTurn());
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
    //new VisionTurn().schedule();
    new MotionVision().schedule();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  //   if(m_autonomousCommand!=null && m_autonomousCommand.isFinished())
  //   m_autonomousCommand=null;
  // if(m_autonomousCommand==null && getCentered()==false)
  // {
  //   System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
  //   m_autonomousCommand = new VisionTurn();
  //   m_autonomousCommand.schedule();
  // }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
  public void setCentered(boolean centered)
  {
    targetCentered = centered;
  }
  public boolean getCentered()
  {
    return targetCentered;
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
