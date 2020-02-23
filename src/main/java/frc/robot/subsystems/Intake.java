package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.*;


public class Intake extends SubsystemBase
{ 
    private double proximityVoltage;
    private double previousVoltage;
    private SpeedController intake;  
    private AnalogInput proximitySensor;

    public Intake(SpeedController intake, AnalogInput proximitySensor) 
    {
         this.intake = intake;
         this.proximitySensor = proximitySensor;
         proximityVoltage = previousVoltage = getDistance();
    }
    public void moveIntake(double speed)
    {
        intake.set(speed);
    }
    public void stop()
    {
       intake.stopMotor();
    }

    public double getDistance()
    {
        return (1/proximitySensor.getVoltage())*6.1111126 * 1/2.54;
    }

    @Override
    public void periodic()
    {
        proximityVoltage = getDistance();

        if((proximityVoltage - previousVoltage) >= Constants.INTAKE_PROXIMITY_DIFFERENCE)
        {
            RobotContainer.ballCount++;
            RobotContainer.getTransport().stop();
        }

        previousVoltage = proximityVoltage;
    }
}