package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveTransport extends Command
{
    private double speed;

    public MoveTransport(double speed)
    {
        requires(RobotContainer.getTransport());
        this.speed = speed;

    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
        RobotContainer.getTransport().moveTransport(speed);
    }

    @Override
    protected boolean isFinished() 
    {
        return !RobotContainer.getJoy().getRawButtonPressed(Constants.TRANSPORT_BUTTON);
    }

    @Override
    protected void end() {
        RobotContainer.getTransport().stop();
    }

}