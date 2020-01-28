/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PointTurn extends PIDCommand {
  /**
   * Creates a new PointTurn.
   */
  private static double angle;
  private static double angleTolerance = Constants.TURN_TOLERANCE;
  public PointTurn(double setAngle) {
    super(
        // The controller that the command will use
        new PIDController(0.01145, 0.001, 0.00178),
        // This should return the measurement
        () -> RobotContainer.getAHRS().getAngle(),
        // This should return the setpoint (can also be a constant)
        () -> setAngle,
        // This uses the output
        output -> {
          // Use the output here
          RobotContainer.getDriveTrain().tankDrive(output, -output);
        });
        this.angle = setAngle;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double trackError = angle - RobotContainer.getAHRS().getAngle();
    return Math.abs(trackError) < angleTolerance;
  }
}
