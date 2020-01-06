package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory.State;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class MotionProfile extends CommandBase
{
    private double initTime;
    private double curTime;
    public double trackError, positionError, derivativeError;
    private double leftSpeed, rightSpeed;
    Trajectory trajectory;
    
    public MotionProfile(final Pose2d start, final Pose2d end, final ArrayList<Translation2d> waypoints) {

        final TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_VELOCITY, Constants.MAX_ACCELERATION);
        trajectory = TrajectoryGenerator.generateTrajectory(start, waypoints, end, config);
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        curTime = Timer.getFPGATimestamp();
        final double timeDiff = curTime - initTime;
        State currentState = trajectory.sample(timeDiff);
        RobotContainer.getDriveTrain().tankDrive(currentState.velocityMetersPerSecond, currentState.velocityMetersPerSecond);
        trackError = currentState.poseMeters.getRotation().getDegrees() - RobotContainer.getAHRS().getAngle();
        double kp = Constants.KP;
        double kd = Constants.KD;
        double ktheta = Constants.K_THETA;
        leftSpeed = -currentState.velocityMetersPerSecond - 
            currentState.accelerationMetersPerSecondSq - (kp * positionError) - (kd * derivativeError) - (ktheta * trackError);


    }
 
    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() 
    {
        double timeDiff = curTime - initTime;
        return timeDiff > trajectory.getTotalTimeSeconds();
    }
 
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) 
    {

    }


}