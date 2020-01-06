package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class DriveWithJoystick extends CommandBase
{

	/**
	 * DriveWithJoystick requires the drive train subsystem.
	 */
	public DriveWithJoystick()
	{}

	/**
   * Called once when the command is started but is not used for anything.
   */
  @Override
  public void initialize() {
  }

	/**
   * Called periodically while the command is not finished. Delivers the joystick
   * inputs into the drive train subsystem in order to drive the robot.
   */
  @Override
  public void execute() {
    //Robot.getDriveTrain().takeJoystickInputs(Robot.oi.getJoy());
  }

	/**
   * Called periodically while the command is running to check when the command is
   * finished.
   *
   * @return false
   */
  @Override
  public boolean isFinished() {
    return false;
  }

	/**
   * Called once when the command is finished. Sets the velocity of the drive
   * train to 0 power.
   */
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getDriveTrain().stop();
  }
}
