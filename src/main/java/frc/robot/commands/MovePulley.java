package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MovePulley extends Command
{
    private double speed;
    public MovePulley(double speed)
    {
        requires(RobotContainer.getPulley());
        this.speed = speed;
    }

    @Override
    protected void initialize() 
    {
        RobotContainer.getPulley().movePulley(speed);
    }

    @Override
    protected void end() {
        RobotContainer.getPulley().stop();
    }

	@Override
    protected boolean isFinished() 
    {
        return RobotContainer.getJoy().getRawButtonPressed(Constants.PULLEY_BUTTON);
	}
}