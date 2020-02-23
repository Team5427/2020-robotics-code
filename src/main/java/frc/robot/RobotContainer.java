/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.commands.MotionProfile;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MovePulley;
import frc.robot.commands.MoveStraight;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.PointTurn;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pulley;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...

  //we will increment this in our commands. 
  public static int ballCount = 0;

  private static Joystick joy;
  private static Button intakeButton;
  private static Button transportButton;
  private static Button pulleyButton;


  private final SpeedController frontLeft, rearLeft;
  private final SpeedController frontRight, rearRight;
  private static SpeedControllerGroup leftDrive;
  private static SpeedControllerGroup rightDrive;
  private static DifferentialDrive drive;
  private static DriveTrain driveTrain;
  private static Transport transport;
  private static SpeedController transportMotor;

  private static SpeedController intakeMotor;
  private static Intake intake;

  private static SpeedController pulleyMotor;
  private static AnalogInput pulleyProximity;
  private static Pulley pulley;

  private static AHRS ahrs;
  private static Encoder encLeft;
  private static Encoder encRight;
  
  //add a AnalogInput named promixity sensor.
  
  private static Command motion;
  private static AnalogInput proximitySensor;
  private static AnalogInput intakeProximity;
  private static AnalogInput transportProximity;
  private static AnalogInput transportProximityTwo;
  private static Encoder shooterEncoder;

  private static TalonSRX shooterMotor;

  private static StringBuilder string = new StringBuilder();

  private static Shooter shooter;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    //frontLeft = new WPI_VictorSPX(Constants.LEFT_TOP_MOTOR);
    //middleLeft = new WPI_VictorSPX(Constants.LEFT_MIDDLE_MOTOR);
    //rearLeft = new WPI_VictorSPX(Constants.LEFT_BOTTOM_MOTOR);
    frontLeft = new Talon(2);
    rearLeft = new Talon(3);
    leftDrive = new SpeedControllerGroup(frontLeft, rearLeft);
    
    //frontRight = new WPI_VictorSPX(Constants.RIGHT_TOP_MOTOR);
    //middleRight = new WPI_VictorSPX(Constants.RIGHT_MIDDLE_MOTOR);
    //rearRight = new WPI_VictorSPX(Constants.RIGHT_BOTTOM_MOTOR);
    frontRight = new Talon(0);
    rearRight = new Talon(1);
    rightDrive = new SpeedControllerGroup(frontRight, rearRight);

    drive = new DifferentialDrive(leftDrive, rightDrive);
    drive.setSafetyEnabled(false);

    driveTrain = new DriveTrain(leftDrive, rightDrive, drive);

    //initialize promixity sensor and add as parameter to intake.

    intakeMotor = new WPI_VictorSPX(Constants.INTAKE_MOTOR);
    intakeProximity = new AnalogInput(Constants.INTAKE_PROXIMITY_SENSOR_PORT);
    intake = new Intake(intakeMotor, intakeProximity);

    transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
    transportProximity = new AnalogInput(Constants.TRANSPORT_PROXIMITY_ONE_SENSOR_PORT);
    transport = new Transport(transportMotor, transportProximity, transportProximityTwo);

    pulleyMotor = new WPI_VictorSPX(Constants.PULLEY_MOTOR);
    pulleyProximity = new AnalogInput(Constants.PULLEY_PROXIMITY_SENSOR_PORT);
    pulley = new Pulley(pulleyMotor, pulleyProximity);

    ahrs = new AHRS(SPI.Port.kMXP);

    //encoders have 1440 as PPR and 360 CPR
    encRight = new Encoder(9,8);
    encRight.setDistancePerPulse(Constants.DISTANCE_PER_PULSE); // cicrumference divided by 1440 (feet)
    encRight.setReverseDirection(true);
    encLeft = new Encoder(4,3);
    encLeft.setDistancePerPulse(Constants.DISTANCE_PER_PULSE); // cicrumference divided by 1440 (feet)
    proximitySensor = new AnalogInput(1);


    //creating a profile
    //COUNTER CLOCKWISE is POSITIVE, CLOCKWISE is NEGATIVE
    motion = new MotionProfile(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(0, 2, new Rotation2d(45)), new ArrayList<Translation2d>());
    // Configure the button bindings

    shooterMotor = new TalonSRX(Constants.SHOOTER_MOTOR);
    shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.K_TIMEOUT_MS);
    shooterMotor.setSensorPhase(true);
    shooter = new Shooter(shooterMotor);


    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    joy = new Joystick(0);

    intakeButton = new JoystickButton(joy, Constants.INTAKE_BUTTON);
    transportButton = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);
    pulleyButton = new JoystickButton(joy, Constants.PULLEY_BUTTON);

    intakeButton.whenPressed(new MoveIntake(Constants.INTAKE_TELEOP_SPEED));
    transportButton.whenPressed(new MoveTransport(Constants.TRANSPORT_TELEOP_SPEED));
    pulleyButton.whenPressed(new MovePulley(Constants.PULLEY_TELEOP_SPEED));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
    // An ExampleCommand will run in autonomous
    return null;
  }

  //just some Accessors that take up space
  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static SpeedControllerGroup getLeftSCG(){return leftDrive;}
  public static SpeedControllerGroup getRightSCG(){return rightDrive;}
  public static DifferentialDrive getDiffDrive(){return drive;}
  public static AHRS getAHRS(){return ahrs;}
  public static Encoder getEncLeft(){return encLeft;}
  public static Encoder getEncRight(){return encRight;}
  public static Joystick getJoy(){return joy;}
  public static AnalogInput getProximitySensor(){return proximitySensor;}
  public static Intake getIntake(){return intake;}
  public static Transport getTransport(){return transport;}
  public static Pulley getPulley(){return pulley;}
  
  public static Shooter getShooter()
  {
    return shooter;
  }

  public static TalonSRX getShooterMotor()
  {
    return shooterMotor;
  }

  public static StringBuilder getBuilder()
  {
    return string;
  }

}
