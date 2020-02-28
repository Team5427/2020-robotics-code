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
        // if(!RobotContainer.getElevator().getLimit() || !RobotContainer.getElevator().getLimit())
        // {
            RobotContainer.getElevator().setSpeed(speed);
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