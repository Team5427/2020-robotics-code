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
  private double constant = .0125;
  double dist;
  double bsd;
  /**
   * Creates a new MoveStraight.
   */
  public VisionTurn() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getDriveTrain());
    //initialize();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain = RobotContainer.getDriveTrain();
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("vision");
  }

  // Called every time the scheduler runs while the command is scheduled.
  //if proportion is a certain amount, center to between the center plus "center"-bottom 
  @Override
  public void execute() 
  {
   dist = table.getEntry("targetDistanceFromCenter").getDouble(999999);
    bsd = table.getEntry("biggestSideDifference").getDouble(999999);



    //    System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
      if(dist==999999)
        ;
      if(bsd<25)
      {
        if(dist>0)
        {
 
            if(dist>55)
              driveTrain.tankDrive(-.23, .23);
            else
              driveTrain.tankDrive(-.15, .15);
  
          
        }
        else if(dist<0)
        {
       
          if(dist<-55)
              driveTrain.tankDrive(.23, -.23);
            else
            
              driveTrain.tankDrive(.15, -.15);
        }
      }
      else if(dist>0)
      {
          if(dist<25)
          driveTrain.tankDrive(-.14, .14);

          if(dist>60)
            driveTrain.tankDrive(-.25, .25);
          else
            driveTrain.tankDrive(-.15, .15);

        
      }
      else if(dist<0)
      {
        if(dist>-25)
        driveTrain.tankDrive(.14,- .14);
        if(dist<-60)
            driveTrain.tankDrive(.25, -.25);
          else
            driveTrain.tankDrive(.15, -.15);
      }
     
    // else if(attempt>0)
    // {
    //   if(dist>0)
    //   {
    //       driveTrain.tankDrive(-.15, .15);  
    //   }
    //   else if(dist<0)
    //   { 
    //         driveTrain.tankDrive(.15, -.15);
    //   }
    // }
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
    

    if(centered)
    {
      // if(Math.abs(dist)<3)
      // {
      //   end(true);
      //   return true;
      // }
    //   if(Math.abs(dist)<3)
    //   {
    //     end(true);
    //     return true;
    //   }
    //   if(attempt<5)
    //     {
    //       attempt++;
    //       return false;
    //     }
     
         end(true);
         return true;
    // } 
    }
 return false;
}
}
