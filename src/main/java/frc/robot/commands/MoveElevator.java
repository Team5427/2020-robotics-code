package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase
{
    private double speed;

    public MoveElevator(double speed)
    {
        addRequirements(RobotContainer.getElevator());
        this.speed = speed;
    }
    @Override
    public void initialize() 
    {
        // if(speed > 0 && !RobotContainer.getElevator().getEncLimit())
        // {
            RobotContainer.getElevator().setSpeed(speed);
        // }
        // else if(speed < 0 && !RobotContainer.getElevator().getLimit())
        // {
            // RobotContainer.getElevator().setSpeed(speed);
        // }
    }

    @Override
    public boolean isFinished() 
    {
        if(speed>0)
        {
            return !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON);
        }
        else
        {
            return !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON);
        }
        
    }

    @Override
    public void end(boolean interrupted) 
    {
        RobotContainer.getElevator().stop();
    }
}