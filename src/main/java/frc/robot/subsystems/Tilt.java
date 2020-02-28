package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;


public class Tilt extends SubsystemBase
{ 
    private SpeedController tiltMotor;  

    public Tilt(SpeedController tiltMotor) 
    {
         this.tiltMotor = tiltMotor;
    }
    public void moveTilt(double speed)
    {
        tiltMotor.set(speed);
    }
    public void stop()
    {
       tiltMotor.stopMotor();
    }
}