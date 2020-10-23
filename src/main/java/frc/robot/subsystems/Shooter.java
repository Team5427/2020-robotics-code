package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase
{
    private SpeedController shooterMotorTop;
    private SpeedController shooterMotorBottom;
    
    public Shooter(SpeedController shooterMotorTop, SpeedController shooterMotorBottom)
    {
        this.shooterMotorTop = shooterMotorTop;
        this.shooterMotorBottom = shooterMotorBottom;
    }

    public SpeedController getShooterMotorTop()
    {
        return shooterMotorTop;
    }

    public SpeedController getShooterMotorBottom()
    {
        return shooterMotorBottom;
    }

    public void moveShooter(double speed)
    {
        shooterMotorBottom.set(-speed);
        shooterMotorTop.set(speed);
    }

    public void stop()
    {
        shooterMotorBottom.set(0);
        shooterMotorTop.set(0);
    }
}