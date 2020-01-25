
package main.java.frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class turn extends Command {
  String direction;
  String compare;

  /**
   * Creates a new Rotate.
   */
  public turn(String Which) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drivetrain);
    direction = Which;
    compare = "Left";

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.drivetrain.resetLeftEncoder();
    RobotContainer.drivetrain.resetRightEncoder();
    if ((direction.compareToIgnoreCase(compare)) == 0) {
      RobotContainer.drivetrain.setRightMotorPosition(-Constants.turnLeft);
      RobotContainer.drivetrain.setLeftMotorPosition(Constants.turnLeft);
    } else {
      RobotContainer.drivetrain.setRightMotorPosition(-Constants.turnRight);
      RobotContainer.drivetrain.setLeftMotorPosition(Constants.turnRight);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.setLeftMotorPosition(RobotContainer.drivetrain.getLeftMotorEncoder());
    RobotContainer.drivetrain.setRightMotorPosition(RobotContainer.drivetrain.getRightMotorEncoder());

    if (interrupted == true) {
      RobotContainer.drivetrain.setLeftMotorSpeed(0);
      RobotContainer.drivetrain.setLeftMotorSpeed(0);
    }
  }

 
  @Override
  public boolean isFinished() {
 
    if ((RobotContainer.drivetrain.getLeftMotorEncoder() == Constants.turnLeft)
        && (RobotContainer.drivetrain.getRightMotorEncoder() == Constants.turnLeft))
      return true;
    return false;
  }
}