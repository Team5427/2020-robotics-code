package frc.robot.commands.auto;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MoveStraight;
import frc.robot.commands.MoveStraightPID;
import frc.robot.commands.PointTurn;
import frc.robot.subsystems.DriveTrain;

public class Arc extends SequentialCommandGroup
{
    public Arc(DriveTrain driveTrain)
    {
        super
        (
            
            new MoveStraightPID(2),
            new PointTurn(90),
            new MoveStraightPID(2)
           
        );
    }
}