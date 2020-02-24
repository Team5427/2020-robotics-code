package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase
{
    @Override
    public void initialize() 
    {
        if(!RobotContainer.getElevator().getLimit() || !RobotContainer.getElevator().getLimit())
        {
            RobotContainer.getElevator().setSpeed(Constants.ELEVATOR_SPEED);
        }
    }

    @Override
    public boolean isFinished() 
    {
        return RobotContainer.getElevator().getLimit() || RobotContainer.getElevator().getLimit();
    }

    @Override
    public void end(boolean interrupted) 
    {
        RobotContainer.getElevator().stop();
    }
}