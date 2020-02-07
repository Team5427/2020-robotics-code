package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class RotationControl extends Command
{
    private char color;
    private char previous = '0';
    private int count;

    public RotationControl()
    {
        requires(RobotContainer.getColorSensor());
    }

    @Override
    protected void initialize() 
    {
        color = RobotContainer.getColorSensor().getColor();
        if(color != '0')
        {
            RobotContainer.getColorSensor().run(Constants.COLOR_WHEEL_SPEED);
        }
        else
        {
            end();
        }
    }

    @Override
    protected void execute() 
    {
        if(color != previous && RobotContainer.getColorSensor().getColor() == color)
        {
            count++;
        }
        previous = RobotContainer.getColorSensor().getColor();
    }

    @Override
    protected boolean isFinished() 
    {
        // TODO Auto-generated method stub
        return count >= 6;
    }
    
}