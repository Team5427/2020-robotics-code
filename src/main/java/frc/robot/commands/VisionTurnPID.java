package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class VisionTurnPID extends CommandBase
{
    private NetworkTableInstance instance;
    private NetworkTable table;
    private double biasAngle;
    private double angle;
    private PointTurn pointTurn;

    private double startTime;
    private double currTime;
    
    public VisionTurnPID(double biasAngle)
    {
        addRequirements(RobotContainer.getDriveTrain());
        this.biasAngle = biasAngle;
        startTime = currTime = 0;
    }

    @Override
    public void initialize() 
    {
        instance = NetworkTableInstance.getDefault();
        table = instance.getTable("vision");
        angle = table.getEntry("yawToTarget").getDouble(0);
        angle += biasAngle;
        pointTurn = new PointTurn(angle);
        pointTurn.schedule();
        startTime = currTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() 
    {
        currTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() 
    {
        if(currTime - startTime >= 5.0)
        {
            return true;
        }
        return pointTurn.isFinished();
    }

    @Override
    public void end(boolean interrupted) 
    {
        pointTurn.end(false);
        RobotContainer.getDriveTrain().stop();
    }


}