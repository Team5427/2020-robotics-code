// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class Shooter extends SubsystemBase
// {
//     private WPI_TalonSRX shooterMotor;
    
//     public Shooter(WPI_TalonSRX shooterMotor)
//     {
//         this.shooterMotor = shooterMotor;
//     }

//     public void setSpeed(double speed)
//     {
//         shooterMotor.set(speed);
//     }

//     public double getVelocity()
//     {
//         double velocity = shooterMotor.getSelectedSensorVelocity(0)*((double)600/(double)4096);   
//         return velocity;
//     }
// }

// // package frc.robot.subsystems;

// // import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// // import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// // import com.ctre.phoenix.motorcontrol.can.*;
// // import com.ctre.phoenix.motorcontrol.*;

// // import edu.wpi.first.wpilibj.Encoder;
// // import edu.wpi.first.wpilibj.SpeedController;
// // import edu.wpi.first.wpilibj.controller.PIDController;
// // import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
// // import edu.wpi.first.wpilibj2.command.PIDSubsystem;
// // import frc.robot.Constants;

// // public class Shooter extends PIDSubsystem
// // {
// //     private WPI_TalonSRX shooterMotor;

// //     private Encoder shooterEncoder;

// //     private SimpleMotorFeedforward feedForward;

// //     public Shooter(SpeedController shooterMotor, Encoder shooterEncoder) 
// //     {
// //         super(new PIDController(Constants.kP_SHOOTER, Constants.kI_SHOOTER, Constants.kD_SHOOTER));
// //         this.shooterMotor = shooterMotor;
// //         this.shooterEncoder = shooterEncoder;
// //         feedForward = new SimpleMotorFeedforward(Constants.kS_SHOOTER, Constants.kV_SHOOTER);
// //         shooterMotor.setPID(Constants.kP_SHOOTER, Constants.kI_SHOOTER, Constants.kD_SHOOTER);
// //         shooterMotor.setF(feedForward);
// //         shooterMotor.setSetpoint(Constants.TARGET_SHOOTER_RPM);

// //     }

// //     protected void useOutput(double output, double setpoint) 
// //     {
// //         //shooterMotor.setVoltage(getController().calculate(shooterEncoder.getRate(), setpoint));
// //         shooterMotor.setVoltageCompensationRampRate(feedForward.calculate(setpoint) + output);
// //     }


// //     protected double getMeasurement() 
// //     {
// //         return shooterEncoder.getRate();
// //     }

// //     public boolean atSetpoint()
// //     {
// //         if((shooterMotor.getPosition() + Constants.SHOOTER_TOLERANCE > shooterMotor.getSetpoint()) && (shooterMotor.getPosition() - Constants.SHOOTER_TOLERANCE < shooterMotor.getSetpoint()))
// //             return true;
// //         return false;
// //     }
// // }