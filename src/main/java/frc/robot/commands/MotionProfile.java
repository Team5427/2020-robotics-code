package frc.robot.commands;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory.State;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class MotionProfile extends CommandBase
{
    private double initTime;
    private double curTime;
    private double lastTimeDiff;
    private double cummulativeDistance;
    private double currentDistance;
    private double lastPositionError;
    public double trackError, positionError, derivativeError;
    private double leftSpeed, rightSpeed;
    private Trajectory trajectory;
    private DriveTrain driveTrain;
    private AHRS ahrs;
    private Encoder encLeft;
    private Encoder encRight;
    private double kp = Constants.KP;
    private double kd = Constants.KD;
    private double ktheta = Constants.K_THETA;
    
    public MotionProfile(final Pose2d start, final Pose2d end, final ArrayList<Translation2d> waypoints) {
        driveTrain = RobotContainer.getDriveTrain();
        ahrs = RobotContainer.getAHRS();
        encLeft = RobotContainer.getEncLeft();
        encRight = RobotContainer.getEncRight();
        TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_VELOCITY, Constants.MAX_ACCELERATION);
        trajectory = TrajectoryGenerator.generateTrajectory(start, waypoints, end, config);
    }

    @Override
    public void initialize() {
        ahrs.reset();
        encRight.reset();
        encLeft.reset();
        initTime = Timer.getFPGATimestamp();
        lastTimeDiff = 0;
        cummulativeDistance = 0;
        lastPositionError = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        //calculate time difference since the time command started
        curTime = Timer.getFPGATimestamp();
        double timeDiff = curTime - initTime;

        //collect current state of robot in trajectory
        State currentState = trajectory.sample(timeDiff);
        State lastState = trajectory.sample(lastTimeDiff);
        
        //finds error in robot orientation for P controller
        trackError = currentState.poseMeters.getRotation().getDegrees() - ahrs.getAngle();

        //calculates expected distance traveled by the robot
        Translation2d newPt = currentState.poseMeters.getTranslation();
        Translation2d oldPt = lastState.poseMeters.getTranslation();
        double diffDistance = oldPt.getDistance(newPt);
        cummulativeDistance += diffDistance;

        //calculates current distance traveled by the robot
        currentDistance = (encLeft.getDistance() + encRight.getDistance())/2;

        //finds error in robot distance for PD controller
        positionError = cummulativeDistance - currentDistance;
        derivativeError = positionError - lastPositionError;
        
        //calculates speed using P heading controller and PD position controllers
        //angle decreases left speed magnitude while increases right speed magnitude - makes sense if trying to turn
        leftSpeed = -currentState.velocityMetersPerSecond - 
            currentState.accelerationMetersPerSecondSq - (kp * positionError) - (kd * derivativeError) + (ktheta * trackError);

        rightSpeed = currentState.velocityMetersPerSecond +
            currentState.accelerationMetersPerSecondSq + (kp * positionError) + (kd * derivativeError) + (ktheta * trackError);
        
        driveTrain.tankDrive(leftSpeed, rightSpeed);

        lastTimeDiff = timeDiff;
        lastPositionError = positionError;
    }
 
    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished(){
        //finishes if time taken exceeds trajectory's time
        double timeDiff = curTime - initTime;
        return timeDiff > trajectory.getTotalTimeSeconds();
    }
 
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted){
        driveTrain.stop();
    }


}