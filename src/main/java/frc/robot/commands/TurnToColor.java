package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorSensor;

public class TurnToColor extends Command
{
    public TurnToColor()
    {
        requires(RobotContainer.getColorSensor());
    }

    @Override
    public void initialize()
    {
        if(RobotContainer.getColorSensor().getColor() != RobotContainer.color)
        {
            RobotContainer.getColorSensor().run(Constants.COLOR_WHEEL_SPEED);
        }
    }

    @Override
    protected boolean isFinished() 
    {
        // TODO Auto-generated method stub
        return RobotContainer.getColorSensor().getColor() == RobotContainer.color;
    }

    @Override
    public void end()
    {
        RobotContainer.getColorSensor().stop();
    }
}