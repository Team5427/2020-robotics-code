package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
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
        // if(Math.abs(RobotContainer.getTransport().getDistance() - Constants.FIRST_PROXIMITY_UNCOVERED) <= Constants.PROXIMITY_TOLERANCE)
        // {
        //     RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
        // }
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getIntake().stop();
    }

    @Override
    public void execute() {
        RobotContainer.getIntake().moveIntake(speed);
    }

    @Override
    public boolean isFinished()
    {
        return !RobotContainer.getJoy().getRawButton(Constants.INTAKE_BUTTON);
    }
}