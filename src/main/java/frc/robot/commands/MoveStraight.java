/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class MoveStraight extends CommandBase {

  private double time;
  private double startTime;
  private DriveTrain driveTrain;
  private double highestSpeed;
  private double speed;
  private double highestTime;
  /**
   * Creates a new MoveStraight.
   */
  public MoveStraight(double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.time = time;
    addRequirements(RobotContainer.getDriveTrain());
    speed = highestSpeed = highestTime = 0;
    //initialize();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain = RobotContainer.getDriveTrain();
    RobotContainer.getEncLeft().reset();
    RobotContainer.getEncRight().reset();
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double timediff = Timer.getFPGATimestamp() - startTime;
    driveTrain.tankDrive(1.0, 1.0);
    speed = RobotContainer.getDriveTrain().getAvgRate();
    if(speed > highestSpeed)
    {
      highestSpeed = speed;
      highestTime = timediff;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
    System.out.println("Max Velocity: " + highestSpeed + " - Time: " + highestTime);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime > time;
  }

}
