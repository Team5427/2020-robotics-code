package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeC extends Command
{
    private double speed;

    public IntakeC(double speed)
    {
        requires(RobotContainer.getIntake());
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getIntake().moveIntake(speed);
    }

    @Override
    protected boolean isFinished() 
    {
        return !RobotContainer.getJoy().getRawButtonPressed(Constants.INTAKE_BUTTON);
    }

}