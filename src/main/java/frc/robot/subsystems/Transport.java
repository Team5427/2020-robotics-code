package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.*;

public class Transport extends Subsystem
{
    private double proximityVoltage;
    private double previousVoltage;
    private AnalogInput proximitySensor;
    private SpeedController transportMotor;  
   
    
    public Transport (SpeedController transportMotor, AnalogInput proximitySensor) 
    {
        this.proximitySensor =  proximitySensor;
        proximityVoltage = previousVoltage = getDistance();
        this.transportMotor = transportMotor;

    }

    public void stop()
    {
        transportMotor.stopMotor();
    }

    public void moveTransport(double speed)
    {
        transportMotor.set(speed);
    }

    public double getDistance(){
        
        double distance = (1/proximitySensor.getVoltage())*6.1111126 * 1/2.54;
        return distance;
    }
    
    @Override
    public void periodic()
    {
        proximityVoltage = getDistance();

        if((proximityVoltage - previousVoltage) >= Constants.INTAKE_PROXIMITY_DIFFERENCE)
        {
            RobotContainer.ballCount++;
        }

        previousVoltage = proximityVoltage;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

}