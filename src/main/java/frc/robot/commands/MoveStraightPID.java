/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class MoveStraightPID extends PIDCommand {
  /**
   * Creates a new MoveStraightPID.
   */
  private static double displacement;
  private static double driveTolerance = Constants.DRIVE_TOLERANCE;
  private double trackerror = 0;
  private double encoderAverage = 0;
  public MoveStraightPID(double setDisp) {
    super(
        // The controller that the command will use
        new PIDController(0.01, 0, 0),
        // This should return the measurement
        () -> (RobotContainer.getEncLeft().get() + RobotContainer.getEncRight().get()) / 2,
        // This should return the setpoint (can also be a constant)
        () -> setDisp,
        // This uses the output
        output -> {
          // Use the output here
          RobotContainer.getDriveTrain().tankDrive(output, output);
        });
        this.displacement = setDisp;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    encoderAverage = (RobotContainer.getEncLeft().get() + RobotContainer.getEncRight().get()) / 2;
    trackerror = displacement - encoderAverage;
    return Math.abs(trackerror) < driveTolerance;
  }
}
