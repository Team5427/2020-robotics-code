package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Transport;

public class MoveShooter extends CommandBase
{
    private int loops = 0;
    private double velocity = 0;
    private double targetVelocityTop = -20;
    private double targetVelocityBottom = -20;
    private double motorOutputTop = 0;
    private double motorOutputBottom = 0;

    private double topError = 0;
    private double bottomError = 0;

    public MoveShooter()
    {
        addRequirements(RobotContainer.getShooter(), RobotContainer.getPulley());
    }

    @Override
    public void initialize() 
    {
        Transport.shooterPressed = true;
        if(Transport.proximityVoltageTwo >= Constants.PROXIMITY_UNCOVERED)
        {
            RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
        }
    }

    @Override
    public void execute() {
        motorOutputTop = RobotContainer.getShooter().getMotorOutputPercentTop();
        SmartDashboard.putNumber("Velocity Top", RobotContainer.getShooter().getVelocityTop());
        SmartDashboard.putNumber("Velocity Bottom", RobotContainer.getShooter().getVelocityBottom());

        RobotContainer.getShooter().getShooterMotorTop().set(ControlMode.Velocity, targetVelocityTop*((double)4096/(double)600));
        RobotContainer.getShooter().getShooterMotorBottom().set(ControlMode.Velocity, targetVelocityBottom*((double)4096/(double)600));

        topError = RobotContainer.getShooter().getShooterMotorTop().getClosedLoopError(Constants.SHOOTER_PID_ID)*((double)600/(double)4096);
        bottomError = RobotContainer.getShooter().getShooterMotorBottom().getClosedLoopError(Constants.SHOOTER_PID_ID)*((double)600/(double)4096);
        SmartDashboard.putNumber("Error Top", topError);
        SmartDashboard.putNumber("Error Bottom", bottomError);


        // if(topError <= Constants.SHOOTER_ERROR_TOLERANCE && bottomError <= Constants.SHOOTER_ERROR_TOLERANCE)
        // {
        //     RobotContainer.loop++;
        // }
        // else
        // {
        //     RobotContainer.loop = 0;
        // }

        // if(RobotContainer.loop >= 2)
        // {
        //     RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
        //     RobotContainer.loop = 0;
        // }
    }

    @Override
    public boolean isFinished() {
        return RobotContainer.ballsOut == RobotContainer.ballsIn;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
    }
}