/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here...
  private static Joystick joy;
  private final SpeedController frontLeft, middleLeft, rearLeft;
  private final SpeedController frontRight, middleRight, rearRight;
  private static SpeedController colorMotor;
  private static SpeedControllerGroup leftDrive;
  private static SpeedControllerGroup rightDrive;
  private static DifferentialDrive drive;

  private static DriveTrain driveTrain;

  private static AHRS ahrs;
  private static Encoder encLeft;
  private static Encoder encRight;
  private static ColorSensor colorSensor;
  private static ColorSensorV3 cs;
  private static I2C.Port i2cport;

  public static AnalogInput analogUltrasonic;
  public static PWM pwmUltrasonic;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {

    frontLeft = new WPI_VictorSPX(Constants.LEFT_TOP_MOTOR);
    middleLeft = new WPI_VictorSPX(Constants.LEFT_MIDDLE_MOTOR);
    rearLeft = new WPI_VictorSPX(Constants.LEFT_BOTTOM_MOTOR);
    leftDrive = new SpeedControllerGroup(frontLeft, middleLeft, rearLeft);
    
    frontRight = new WPI_VictorSPX(Constants.RIGHT_TOP_MOTOR);
    middleRight = new WPI_VictorSPX(Constants.RIGHT_MIDDLE_MOTOR);
    rearRight = new WPI_VictorSPX(Constants.RIGHT_BOTTOM_MOTOR);
    rightDrive = new SpeedControllerGroup(frontRight, middleRight, rearRight);

    drive = new DifferentialDrive(leftDrive, rightDrive);
    drive.setSafetyEnabled(false);

    driveTrain = new DriveTrain(leftDrive, rightDrive, drive);

    ahrs = new AHRS(SPI.Port.kMXP);

    colorMotor = new WPI_VictorSPX(0);// change port value
    i2cport = I2C.Port.kOnboard;
    analogUltrasonic = new AnalogInput(0);
    pwmUltrasonic = new PWM(0);


    //encoders have 1440 as PPR and 360 CPR
    encRight = new Encoder(Constants.ENCODER_RIGHT_PORT_1, Constants.ENCODER_RIGHT_PORT_2);
    encRight.setDistancePerPulse(Constants.DISTANCE_PER_PULSE); // cicrumference divided by 1440 (feet)
    encLeft = new Encoder(Constants.ENCODER_LEFT_PORT_1, Constants.ENCODER_LEFT_PORT_2);
    encLeft.setDistancePerPulse(Constants.DISTANCE_PER_PULSE); // cicrumference divided by 1440 (feet)
    cs = new ColorSensorV3(i2cport);
    colorSensor = new ColorSensor(colorMotor, cs);
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
  public static ColorSensor getColorSensor()
  {
    return colorSensor;
  }

  public static double PWMUltrasonicDistance() {
    double rawDist = pwmUltrasonic.getRawBounds();
    System.out.println(rawDist);
    return rawDist;
  }

  public static double AnalogUltrasonicDistance() {
    double rawDist = analogUltrasonic.getValue()-217;
    System.out.println(rawDist);
    return rawDist;
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
}