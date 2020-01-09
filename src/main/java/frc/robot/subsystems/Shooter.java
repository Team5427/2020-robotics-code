package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Shooter extends PIDSubsystem 
{
    private SpeedController shooterMotor;

    private Encoder shooterEncoder;
    //comment

    private SimpleMotorFeedforward feedForward;

    public Shooter(SpeedController shooterMotor, Encoder shooterEncoder) 
    {
        super(new PIDController(Constants.kP_SHOOTER, Constants.kI_SHOOTER, 
            Constants.kD_SHOOTER, Constants.SHOOTER_PERIOD));
        this.shooterMotor = shooterMotor;
        this.shooterEncoder = shooterEncoder;
        getController().setTolerance(Constants.SHOOTER_TOLERANCE);
        setSetpoint(Constants.TARGET_SHOOTER_RPM);
        feedForward = new SimpleMotorFeedforward(Constants.kS_SHOOTER, Constants.kV_SHOOTER);
    }

    @Override
    protected void useOutput(double output, double setpoint) 
    {
        //shooterMotor.setVoltage(getController().calculate(shooterEncoder.getRate(), setpoint));
        shooterMotor.setVoltage(feedForward.calculate(setpoint) + output);
    }

    @Override
    protected double getMeasurement() 
    {
        return shooterEncoder.getRate();
    }

    public boolean atSetpoint()
    {
        return getController().atSetpoint();
    }
}