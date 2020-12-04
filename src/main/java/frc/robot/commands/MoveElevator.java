package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase
{
    private double speed;
    private int leftCount, rightCount;
    public static double leftLimit = 6259.75;
    public static double rightLimit = -6264.0;

    public MoveElevator(double speed)
    {
        addRequirements(RobotContainer.getElevator());
        this.speed = speed;
    }
    @Override
    public void initialize() 
    {
        if(speed > 0 && (RobotContainer.getElevator().getLeftEnc().getDistance() <= leftLimit && RobotContainer.getElevator().getRightEnc().getDistance() >= rightLimit))
        {
            RobotContainer.getElevator().setSpeed(speed);
        }
        if(speed < 0 && (!RobotContainer.getElevator().getLimitLeft() || !RobotContainer.getElevator().getLimitRight()))
        {
            RobotContainer.getElevator().setSpeed(speed);
        }
    }

    @Override
    public void execute() 
    {
        //right: -5383.0
        //left: 5390.5
        if(speed < 0)
        {
            if(RobotContainer.getElevator().getLimitLeft())
            {
                    RobotContainer.getElevator().setLeft(0);
            }
            if(RobotContainer.getElevator().getLimitRight())
            {
                    RobotContainer.getElevator().setRight(0);
            }
        }
        if(speed > 0)
        {
            if(RobotContainer.getElevator().getLeftEnc().getDistance() >= leftLimit)
            {
                RobotContainer.getElevator().setLeft(0);
            }
            if(RobotContainer.getElevator().getRightEnc().getDistance() <= rightLimit)
            {
                RobotContainer.getElevator().setRight(0);
            }
        }
    }

    public boolean getEncLimits()
    {
        return RobotContainer.getElevator().getLeftEnc().getDistance() >= leftLimit && RobotContainer.getElevator().getRightEnc().getDistance() <= rightLimit;
    }


    @Override
    public boolean isFinished() 
    {
        if(speed>0)
        {
            return getEncLimits() || !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON);
        }
        else
        {
            return (RobotContainer.getElevator().getLimitLeft() && RobotContainer.getElevator().getLimitRight()) 
                || !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON);
        }
        
    }

    @Override
    public void end(boolean interrupted) 
    {
        RobotContainer.getElevator().stop();
    }
}