package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase
{
    private TalonSRX shooterMotor;
    
    public Shooter(TalonSRX shooterMotor)
    {
        this.shooterMotor = shooterMotor;
        shooterMotor.configNominalOutputForward(0, Constants.K_TIMEOUT_MS);
        shooterMotor.configNominalOutputReverse(0, Constants.K_TIMEOUT_MS);
        shooterMotor.configPeakOutputForward(1, Constants.K_TIMEOUT_MS);
        shooterMotor.configPeakOutputReverse(-1, Constants.K_TIMEOUT_MS);

        shooterMotor.config_kF(Constants.SHOOTER_PID_ID, Constants.kF_SHOOTER, Constants.K_TIMEOUT_MS);
        shooterMotor.config_kP(Constants.SHOOTER_PID_ID, Constants.kP_SHOOTER, Constants.K_TIMEOUT_MS);
        shooterMotor.config_kI(Constants.SHOOTER_PID_ID, Constants.kI_SHOOTER, Constants.K_TIMEOUT_MS);
        shooterMotor.config_kD(Constants.SHOOTER_PID_ID, Constants.kD_SHOOTER, Constants.K_TIMEOUT_MS);
    }

    public double getVelocity()
    {
        double velocity = shooterMotor.getSelectedSensorVelocity(0)*((double)600/(double)4096);   
        return velocity;
    }

    public double getMotorOutputPercent()
    {
        return shooterMotor.getMotorOutputPercent();
    }

    public TalonSRX getShooterMotor()
    {
        return shooterMotor;
    }
}