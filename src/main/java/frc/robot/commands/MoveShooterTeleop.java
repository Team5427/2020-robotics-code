package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase
{
    private double speed;
    public MoveShooterTeleop(double speed)
    {
        addRequirements(RobotContainer.getShooter());
        this.speed = speed;
    }

    @Override
    public void initialize() {
        RobotContainer.getShooter().moveShooter(speed);
        // RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        // RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
    }

    @Override
    public void execute() {
        // RobotContainer.getShooter().moveShooter(speed);
        // RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_SHOOTING_SPEED);
        // RobotContainer.getPulley().movePulley(Constants.PULLEY_SHOOTING_SPEED);
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.SHOOTER_TELEOP);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
        // RobotContainer.getTransport().stop();
        // RobotContainer.getPulley().stop();
    }
}