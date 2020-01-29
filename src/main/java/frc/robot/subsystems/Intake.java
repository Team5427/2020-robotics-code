package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.*;


public class Intake extends SubsystemBase 
{
    private SpeedController intake;
    Robot prV =  new Robot();
    private int amtBallsHeld = 0;    

    public Intake(SpeedController intake) 
    {
         this.intake = intake;
    }
    public void moveIntake(double Intake)
    {
        intake.set(Intake);
    }
    public void stop()
    {
       intake.stopMotor();
    }
    public void increaseBalls()
    {
        //if proximity in certain range, then increase ball count
        if (prV.getProximityVoltage()==4 && prV.getProximityVoltage()==1)
        {
            increaseBalls();
            amtBallsHeld++;
        }
    }
}