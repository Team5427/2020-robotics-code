package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntegratedIntake extends CommandBase 
{
    public IntegratedIntake()
    {
        addRequirements(RobotContainer.getIntake(), RobotContainer.getTransport(), RobotContainer.getPulley());
    }

    @Override
    public void initialize() 
    {
        RobotContainer.getIntake().moveIntake(Constants.INTAKE_INTEGRATED_SPEED);
    }

    @Override
    public void execute() {
        
    }

}