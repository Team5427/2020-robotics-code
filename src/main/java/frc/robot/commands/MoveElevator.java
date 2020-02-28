package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase
{

    public MoveElevator()
    {
        addRequirements(RobotContainer.getElevator());
    }
    @Override
    public void initialize() 
    {
        // if(!RobotContainer.getElevator().getLimit() || !RobotContainer.getElevator().getLimit())
        // {
            RobotContainer.getElevator().setSpeed(Constants.ELEVATOR_SPEED);
        // }
    }

    @Override
    public boolean isFinished() 
    {
        return RobotContainer.getElevator().getLimit() || !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_BUTTON);
    }

    @Override
    public void end(boolean interrupted) 
    {
        RobotContainer.getElevator().stop();
    }
}