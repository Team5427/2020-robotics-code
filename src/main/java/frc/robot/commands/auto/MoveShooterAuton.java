package frc.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooterAuton extends CommandBase
{
    public MoveShooterAuton()
    {

    }

    @Override
    public void initialize() {
        RobotContainer.getShooter().getShooterMotorTop().set(ControlMode.PercentOutput, -Constants.SHOOTER_UP_SPEED);
        RobotContainer.getShooter().getShooterMotorBottom().set(ControlMode.PercentOutput, -Constants.SHOOTER_DOWN_SPEED);
        double seconds = 0.2;
        double start = Timer.getFPGATimestamp();
        while(Timer.getFPGATimestamp()-start < seconds)
        {
        }
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
    }

    @Override
    public void execute() 
    {
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
        RobotContainer.getShooter().getShooterMotorTop().set(ControlMode.PercentOutput, -Constants.SHOOTER_UP_SPEED);
        RobotContainer.getShooter().getShooterMotorBottom().set(ControlMode.PercentOutput, -Constants.SHOOTER_DOWN_SPEED);
    }
    

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
        RobotContainer.getPulley().stop();
    }
}