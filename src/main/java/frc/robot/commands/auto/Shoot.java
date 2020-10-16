package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shoot extends CommandBase
{
    public Shoot()
    {

    }

    @Override
    public void initialize()
    {
        RobotContainer.getShooter().moveShooter(Constants.SHOOTER_DOWN_SPEED);
    }

    @Override
    public void execute()
    {

    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        
    }
}