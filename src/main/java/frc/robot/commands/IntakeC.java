package frc.robot.commands;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotContainer;

public class IntakeC extends Command
{
    SpeedController intakeMotor;
    public double speed;

    public IntakeC(int speed,SpeedController transportMotor)
    {
        this.speed =  speed;
        this.intakeMotor =  intakeMotor;
    }
@Override
    public void initialize()
    {
        intakeMotor.set(speed); 
    }

    @Override
    public void execute()
    {
    }

    

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end()
    {
        //RobotContainer.getTransportMotor().stopMotor();
    }
}