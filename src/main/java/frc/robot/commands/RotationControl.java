package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class RotationControl extends CommandBase
{
    private char color;
    private char previous = '0';
    private int count;

    public RotationControl()
    {
        addRequirements(RobotContainer.getColorSensor());
    }

    @Override
    public void initialize() 
    {
        color = RobotContainer.getColorSensor().getColor();
        if(color != '0')
        {
            RobotContainer.getColorSensor().run(Constants.COLOR_WHEEL_SPEED);
        }
        else
        {
            end(true);
        }
    }

    @Override
    public void execute() 
    {
        if(color != previous && RobotContainer.getColorSensor().getColor() == color)
        {
            count++;
        }
        previous = RobotContainer.getColorSensor().getColor();
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getColorSensor().stop();
    }

    @Override
    public boolean isFinished() 
    {
        // TODO Auto-generated method stub
        return count >= 6;
    }
    
}