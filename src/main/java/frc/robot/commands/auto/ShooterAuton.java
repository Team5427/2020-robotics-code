package frc.robot.commands.auto;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MotionProfile;
import frc.robot.commands.VisionTurn;

public class ShooterAuton extends SequentialCommandGroup
{
    public ShooterAuton()
    {
       super(new MotionProfile(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(-2, 0, new Rotation2d(0)), new ArrayList<Translation2d>()),
        new VisionTurn(0), new MoveShooterAuton());
    }
}