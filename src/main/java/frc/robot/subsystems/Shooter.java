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
    private TalonSRX shooterMotorTop;
    private TalonSRX shooterMotorBottom;
    
    public Shooter(TalonSRX shooterMotorTop, TalonSRX shooterMotorBottom)
    {
        this.shooterMotorTop = shooterMotorTop;
        this.shooterMotorBottom = shooterMotorBottom;
        shooterMotorTop.configNominalOutputForward(0, Constants.K_TIMEOUT_MS);
        shooterMotorTop.configNominalOutputReverse(0, Constants.K_TIMEOUT_MS);
        shooterMotorTop.configPeakOutputForward(1, Constants.K_TIMEOUT_MS);
        shooterMotorTop.configPeakOutputReverse(-1, Constants.K_TIMEOUT_MS);
        
        shooterMotorBottom.configNominalOutputForward(0, Constants.K_TIMEOUT_MS);
        shooterMotorBottom.configNominalOutputReverse(0, Constants.K_TIMEOUT_MS);
        shooterMotorBottom.configPeakOutputForward(1, Constants.K_TIMEOUT_MS);
        shooterMotorBottom.configPeakOutputReverse(-1, Constants.K_TIMEOUT_MS);

        shooterMotorTop.config_kF(Constants.SHOOTER_PID_ID, Constants.kF_SHOOTER, Constants.K_TIMEOUT_MS);
        shooterMotorTop.config_kP(Constants.SHOOTER_PID_ID, Constants.kP_SHOOTER, Constants.K_TIMEOUT_MS);
        shooterMotorTop.config_kI(Constants.SHOOTER_PID_ID, Constants.kI_SHOOTER, Constants.K_TIMEOUT_MS);
        shooterMotorTop.config_kD(Constants.SHOOTER_PID_ID, Constants.kD_SHOOTER, Constants.K_TIMEOUT_MS);
        
        shooterMotorBottom.config_kF(Constants.SHOOTER_PID_ID, Constants.kF_SHOOTER_BOTTOM, Constants.K_TIMEOUT_MS);
        shooterMotorBottom.config_kP(Constants.SHOOTER_PID_ID, Constants.kP_SHOOTER_BOTTOM, Constants.K_TIMEOUT_MS);
        shooterMotorBottom.config_kI(Constants.SHOOTER_PID_ID, Constants.kI_SHOOTER_BOTTOM, Constants.K_TIMEOUT_MS);
        shooterMotorBottom.config_kD(Constants.SHOOTER_PID_ID, Constants.kD_SHOOTER_BOTTOM, Constants.K_TIMEOUT_MS);
    }

    public double getVelocityTop()
    {
        double velocity = shooterMotorTop.getSelectedSensorVelocity(0)*((double)600/(double)4096);   
        return velocity;
    }

    public double getVelocityBottom()
    {
        double velocity = shooterMotorBottom.getSelectedSensorVelocity(0)*((double)600/(double)4096);   
        return velocity;
    }

    public double getMotorOutputPercentTop()
    {
        return shooterMotorTop.getMotorOutputPercent();
    }

    public double getMotorOutputPercentBottom()
    {
        return shooterMotorBottom.getMotorOutputPercent();
    }

    public TalonSRX getShooterMotorTop()
    {
        return shooterMotorTop;
    }

    public TalonSRX getShooterMotorBottom()
    {
        return shooterMotorBottom;
    }

    public void moveShooter(double speed)
    {
        shooterMotorBottom.set(ControlMode.PercentOutput, -speed);
        shooterMotorTop.set(ControlMode.PercentOutput, speed);
    }

    public void stop()
    {
        shooterMotorBottom.set(ControlMode.PercentOutput, 0);
        shooterMotorTop.set(ControlMode.PercentOutput, 0);
    }
}