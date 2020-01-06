
// Put methods for controlling this subsystem
// here. Call these from Commands.
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveWithJoystick;


/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SpeedControllerGroup left;
  private SpeedControllerGroup right;
  private DifferentialDrive drive;

  /**A toggle for lowlow gear and low gear */
  public static boolean lowlowgear = false;
  
  public DriveTrain(){
    this.left = RobotContainer.getLeftSCG();
    this.right = RobotContainer.getRightSCG();
    this.drive = RobotContainer.getDiffDrive();
  }

  /**Sets up arcadeDrive joystick mapping given boolean lowlowgear */
	public void takeJoystickInputs(Joystick joy) {
		drive.arcadeDrive(joy.getY(), -joy.getZ() * Constants.Z_ROT_DAMPENING);
	}
  
  /** Sets DriveTrain Speed */
	public void tankDrive(double leftSpeed, double rightSpeed)
	{
		right.set(rightSpeed);
		left.set(leftSpeed);
	}

  /** Stop Subsystem*/
	public void stop() {
		drive.stopMotor();
	}

  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoystick());
  }

}
