package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class VisionMotionProfile extends CommandBase
{
    private NetworkTableInstance instance;
    private NetworkTable table;
    private double distance;
    private MotionProfile motionProfile;

    private double startTime;
    private double currentTime;

    public VisionMotionProfile()
    {
        startTime = currentTime = 0;
        addRequirements(RobotContainer.getDriveTrain());
    }

    @Override
    public void initialize() 
    {
        instance = NetworkTableInstance.getDefault();
        table = instance.getTable("vision");
        distance = table.getEntry("distanceToTarget").getDouble(0);
        motionProfile = new MotionProfile(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(0, distance, new Rotation2d(0)), new ArrayList<Translation2d>());
        motionProfile.schedule();
        startTime = currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() 
    {
        currentTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() 
    {
        if(currentTime - startTime >= 5.0)
        {
            return true;
        }
        return motionProfile.isFinished();
    }

    @Override
    public void end(boolean interrupted) 
    {
        motionProfile.end(false);
        RobotContainer.getDriveTrain().stop();
    }
}