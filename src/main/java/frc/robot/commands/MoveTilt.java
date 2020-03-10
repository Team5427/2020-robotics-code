package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class MoveTilt extends CommandBase
{
    private double speed;

    public MoveTilt(double speed)
    {
        addRequirements(RobotContainer.getTilt());
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        if(speed > 0 && !RobotContainer.getTilt().getLimit())
        {
            RobotContainer.getTilt().moveTilt(speed);
        }
        else if(speed < 0)
        {
            RobotContainer.getTilt().moveTilt(speed);
        }
        System.out.println("initalized");
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getTilt().stop();
    }

    @Override
    public void execute() 
    {
        System.out.println(RobotContainer.getTilt().getLimit());
        if(speed > 0)
        {
            if(RobotContainer.getTilt().getLimit())
            {
               RobotContainer.getTilt().moveTilt(0);
            }
        }
        if(speed < 0)
        {
            
        }
    }

    @Override
    public boolean isFinished()
    {
        if(speed > 0)
        {
            return RobotContainer.getTilt().getLimit() || !RobotContainer.getJoy().getRawButton(Constants.TILT_BUTTON_UP);
        }
        else
        {
            return !RobotContainer.getJoy().getRawButton(Constants.TILT_BUTTON_DOWN);
        }
    }
}