/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import  edu.wpi.first.networktables.NetworkTableEntry;
import  edu.wpi.first.networktables.NetworkTableInstance;
import  edu.wpi.first.networktables.NetworkTable;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import frc.robot.subsystems.DriveTrain;

public class VisionTurn extends CommandBase {


  private DriveTrain driveTrain;
  NetworkTableInstance inst;
  NetworkTable table;
  private int attempt =0;
  double dist;
  double bsd;
  boolean angledCentered = false;
  double newDistFromCenter;
  double newCenter;
  double bias = 0;
  double constant = 2;

  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public VisionTurn(double bias) 
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.bias = bias;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    driveTrain = RobotContainer.getDriveTrain();
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("vision");

  }

  @Override
  public void execute() 
  {
    dist = table.getEntry("targetDistanceFromCenter").getDouble(-999999);
    dist -=bias;
    bsd = table.getEntry("biggestSideDifference").getDouble(0);
  
    if(dist==999999)
      ;
 
    else if(bsd<25)
    {

      if(dist>0)
      {
        if(dist>55)
          driveTrain.tankDrive(-.15, .15);
        else
          driveTrain.tankDrive(-.13, .13);
      }

      else if(dist<0)
      {
        if(dist<-55)
          driveTrain.tankDrive(.15, -.15);
        else
          driveTrain.tankDrive(.13, -.13);
      }

    }

    else if(dist>0)
    {
      if(dist<25)
        driveTrain.tankDrive(-.14, .14);
      if(dist>60)
        driveTrain.tankDrive(-.16, .16);
      else
        driveTrain.tankDrive(-.15, .15);
    }

    else if(dist<0)
    {
      if(dist>-25)
        driveTrain.tankDrive(.14,- .14);
      if(dist<-60)
        driveTrain.tankDrive(.16, -.16);
      else
        driveTrain.tankDrive(.15, -.15);
    }
     
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    SmartDashboard.putNumber("attempt", attempt);
    boolean centered = table.getEntry("isTargetCentered").getBoolean(false);
    double proportion = table.getEntry("proportion").getDouble(1);
    dist = table.getEntry("targetDistanceFromCenter").getDouble(0);
    System.out.println("BEFORE BIAS:" +dist);
    dist -=bias;
    dist= Math.abs(dist);

    if(proportion>.9)
      bias = constant * proportion;

    System.out.println("dist:"+dist);
    
    if(dist<3+ bias)
    {
      return true;
    }
    return false;
  }
}