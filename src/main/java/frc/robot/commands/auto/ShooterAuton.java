package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MotionProfile;

public class ShooterAuton extends SequentialCommandGroup
{
    MotionProfile motion;
    public ShooterAuton()
    {
   //     motion = motion = new MotionProfile(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(0, 2, new Rotation2d(45)), new ArrayList<Translation2d>());
      //  super(motion, new MoveShooterAuton());
    }
}