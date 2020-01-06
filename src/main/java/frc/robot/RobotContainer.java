/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TestSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...
  private final SpeedController testMotor1;
  private final SpeedController testMotor2;
  private final TestSubsystem testSub; 
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final SpeedController frontLeft, middleLeft, rearLeft;
  private static SpeedControllerGroup leftDrive;
  private static SpeedControllerGroup rightDrive;
  private final SpeedController frontRight, middleRight, rearRight;
  private static DifferentialDrive drive;

  private static DriveTrain driveTrain;

  private static AHRS ahrs;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    testMotor1 = new WPI_VictorSPX(0);
    testMotor2 = new WPI_VictorSPX(0);
    testSub = new TestSubsystem(testMotor1, testMotor2);

    frontLeft = new WPI_VictorSPX(0);
    middleLeft = new WPI_VictorSPX(0);
    rearLeft = new WPI_VictorSPX(0);
    leftDrive = new SpeedControllerGroup(frontLeft, middleLeft, rearLeft);
    
    frontRight = new WPI_VictorSPX(0);
    middleRight = new WPI_VictorSPX(0);
    rearRight = new WPI_VictorSPX(0);
    rightDrive = new SpeedControllerGroup(frontRight, middleRight, rearRight);

    drive = new DifferentialDrive(leftDrive, rightDrive);

    driveTrain = new DriveTrain();

    ahrs = new AHRS(SPI.Port.kMXP);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public static DriveTrain getDriveTrain()
  {
    return driveTrain;
  }

  public static SpeedControllerGroup getLeftSCG(){
    return leftDrive;
  }

  public static SpeedControllerGroup getRightSCG(){
    return rightDrive;
  }

  public static DifferentialDrive getDiffDrive(){
    return drive;
  }

  public static AHRS getAHRS(){
    return ahrs;
  }
}
