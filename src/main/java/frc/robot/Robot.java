/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MoveStraightPID;
import frc.robot.commands.PointTurn;
import frc.robot.commands.StraightAndTurn;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;

import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Vision.GripPipeline;
import frc.robot.Vision.Target;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
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
  private int status = 0;

  private static CameraServer cameraServer;
  private static AHRS ahrs;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    System.out.println("kuykfwegteiygewkwytriweutwuguy");
    ahrs = new AHRS(SPI.Port.kMXP);
    ahrs.setAngleAdjustment(0);
  
    new Thread(() -> {
      // Initializes Camera from RoboRio and starts capture
       UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
       camera.setResolution(640, 480); // sets resolution
       System.out.println("agshkahrhresl");
      // // Gets video from RoboRio CameraServer [accessible via SmrtDshbrd]
       CvSink cvSink = CameraServer.getInstance().getVideo();
       CvSource outputStream = CameraServer.getInstance().putVideo("Processed", 640, 480);

      Mat source = new Mat(); // Mats are essentially video frame Objects
      GripPipeline pipeline = new GripPipeline();
      camera.setExposureManual(100);
  
      int timer = 0;

      while (!Thread.interrupted()) 
      {
        if (cvSink.grabFrame(source) == 0)
          System.out.print("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        else 
        {
          SmartDashboard.putNumber("Angle", ahrs.getAngle());
          System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
           pipeline.process(source);
           outputStream.putFrame(pipeline.hslThresholdOutput());
           ArrayList<MatOfPoint> filteredPoints = pipeline.filterContoursOutput();
           ArrayList<Target> validTargets=  new ArrayList<Target>();
           Target leftMostTarget = null;

           Point[] points = null;
   
           Target target;
           for(MatOfPoint currentMat : filteredPoints)
           {
              points = ((MatOfPoint)currentMat).toArray();
              target = new Target(points, source);
              validTargets.add(target);
           }
   
           if(validTargets.isEmpty())
           {
              SmartDashboard.putBoolean("Target Exists?", false);
           }
           else{
            SmartDashboard.putBoolean("Target Exists?", true);
            if(validTargets.isEmpty())
            
             continue;
             SmartDashboard.putNumber("Target Amounts", validTargets.size());

          leftMostTarget = validTargets.get(0);
           for(Target t : validTargets)
           {
             // t.getLeftPoint().x<leftMostTarget.getLeftPoint().x 
             //current code for closest to middle, not leftmost
              if(t.getUprightDiff()/t.getSideDiff()>2 || t.getSideDiff()/t.getUprightDiff()>2)
              continue;
               if( t.getProportion()>.9 && t.getProportion()<1.1 && t.getUprightDiff()>leftMostTarget.getUprightDiff())
                   leftMostTarget=t;
           }

           if(ahrs.getAngle()>360)
           {
              ahrs.setAngleAdjustment(ahrs.getAngleAdjustment()+-360);
           }
           if(ahrs.getAngle()<-360)
           {
              ahrs.setAngleAdjustment(ahrs.getAngleAdjustment()+360);
           }
           if(leftMostTarget.isCentered()== false && leftMostTarget.getDistanceFromCenter()<0)
             status =1;
          if(leftMostTarget.isCentered()== false && leftMostTarget.getDistanceFromCenter()>0)
             status = 2;
           if(leftMostTarget.isCentered())
              status = 3;
           SmartDashboard.putNumber("Proportion", leftMostTarget.getProportion());
           SmartDashboard.putNumber("Upright Difference", leftMostTarget.getUprightDiff());
           SmartDashboard.putNumber("Side Difference", leftMostTarget.getSideDiff());
           SmartDashboard.putNumber("DistanceFromCenter", leftMostTarget.getDistanceFromCenter());
           SmartDashboard.putBoolean("isCentered", leftMostTarget.isCentered());


          }

        }
      }
     }).start();
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
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() 
  {
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    //RobotContainer.getDriveTrain().tankDrive(-0.3, 0.3);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(status==1)
    {
      System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
    new PointTurn(-5);
    }
  if(status ==2)
  {
    System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBbb");

    new PointTurn(5);
  }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
}