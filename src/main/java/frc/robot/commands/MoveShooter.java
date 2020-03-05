package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Transport;

public class MoveShooter extends CommandBase
{
    public MoveShooter()
    {
        addRequirements(RobotContainer.getShooter(), RobotContainer.getPulley());
    }

    @Override
    public void initialize() 
    {
        RobotContainer.getShooter().getShooterMotorTop().set(-Constants.SHOOTER_UP_SPEED);
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
    }

    @Override
    public void execute()
    {
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
    }

    @Override
    public boolean isFinished() {
        // return RobotContainer.ballsOut == RobotContainer.ballsIn;
        return !RobotContainer.getJoy().getRawButton(Constants.SHOOTER_TELEOP);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
        RobotContainer.getPulley().stop();
    }
}