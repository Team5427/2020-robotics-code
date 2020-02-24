package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase
{

    public DriveWithJoystick(DriveTrain driveTrain)
    {
        addRequirements(driveTrain);
    }

    @Override
    public void initialize()
    {}

    @Override
    public void execute()
    {
        RobotContainer.getDriveTrain().takeJoystickInputs(RobotContainer.getJoy());
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.getDriveTrain().stop();
    }
}
