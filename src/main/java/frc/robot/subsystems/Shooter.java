package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Shooter
{
    private SpeedController shooterMotor;

    private Encoder shooterEncoder;
    //comment

    private SimpleMotorFeedforward feedForward;

    public Shooter(SpeedController shooterMotor, Encoder shooterEncoder) 
    {
        
        this.shooterMotor = shooterMotor;
        this.shooterEncoder = shooterEncoder;
        feedForward = new SimpleMotorFeedforward(Constants.kS_SHOOTER, Constants.kV_SHOOTER);
        shooterMotor.setPID(Constants.kP_SHOOTER, Constants.kI_SHOOTER, Constants.kD_SHOOTER);
        shooterMotor.setF(feedForward);
        shooterMotor.setSetpoint(Constants.TARGET_SHOOTER_RPM);

    }

    protected void useOutput(double output, double setpoint) 
    {
        //shooterMotor.setVoltage(getController().calculate(shooterEncoder.getRate(), setpoint));
        shooterMotor.setVoltageCompensationRampRate(feedForward.calculate(setpoint) + output);
    }


    protected double getMeasurement() 
    {
        return shooterEncoder.getRate();
    }

    public boolean atSetpoint()
    {
        if((shooterMotor.getPosition() + Constants.SHOOTER_TOLERANCE > shooterMotor.getSetpoint()) && (shooterMotor.getPosition() - Constants.SHOOTER_TOLERANCE < shooterMotor.getSetpoint()))
            return true;
        return false;
    }
}