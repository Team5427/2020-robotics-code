package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Transport;

public class IntakeBall extends CommandBase
{
    public IntakeBall()
    {

    }

    @Override
    public void initialize()
    {
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
    }

    @Override
    public void execute()
    {
        if(RobotContainer.getTransport().getDistanceIntake() < 2.7)
        {
            RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
        }
    }

    @Override
    public boolean isFinished()
    {
        if(RobotContainer.getTransport().getDistanceIntake() > 3.7)
        {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.getTransport().stop();
        RobotContainer.ballCount++;
        Transport.firstSensor = false;
    }
}