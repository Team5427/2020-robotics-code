package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooterAuton extends CommandBase
{
    private double startTime;
    private double currTime;

    public MoveShooterAuton()
    {
        startTime = currTime = 0;
    }

    @Override
    public void initialize() {
        RobotContainer.getShooter().moveShooter(Constants.SHOOTER_UP_SPEED);
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
        startTime = currTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() 
    {
        currTime = Timer.getFPGATimestamp();
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
        RobotContainer.getShooter().moveShooter(Constants.SHOOTER_UP_SPEED);
    }

    @Override
    public void end(boolean interrupted) 
    {
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
        RobotContainer.getPulley().stop();
    }

    @Override
    public boolean isFinished() 
    {
        return currTime - startTime >= 6.0;
    }
}