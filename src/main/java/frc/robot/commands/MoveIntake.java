package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class MoveIntake extends CommandBase
{
    private double speed;

    public MoveIntake(double speed)
    {
        addRequirements(RobotContainer.getIntake());
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getIntake().moveIntake(speed);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getIntake().stop();
    }

    @Override
    public boolean isFinished()
    {
        return !RobotContainer.getJoy().getRawButtonPressed(Constants.INTAKE_BUTTON);
    }
}