package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.*;

public class Transport extends SubsystemBase
{
    private double proximityVoltage;
    private double previousVoltage;
    private AnalogInput proximitySensor;

    private double proximityVoltageTwo;
    private double previousVoltageTwo;
    private AnalogInput proximitySensorTwo;
    private SpeedController transportMotor;  
   
    
    public Transport (SpeedController transportMotor, AnalogInput proximitySensor, AnalogInput proximitySensorTwo) 
    {
        this.proximitySensor =  proximitySensor;
        this.proximitySensorTwo = proximitySensorTwo;
        proximityVoltage = previousVoltage = proximityVoltageTwo = previousVoltageTwo = getDistance();
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

    public double getDistanceTwo()
    {
        double distance = (1/proximitySensorTwo.getVoltage())*6.1111126 * 1/2.54;
        return distance;
    }
    
    @Override
    public void periodic()
    {
        proximityVoltage = getDistance();
        proximityVoltageTwo = getDistanceTwo();

        //something leaves the first sensor
        if((proximityVoltage - previousVoltage) >= Constants.INTAKE_PROXIMITY_DIFFERENCE)
        {
            RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
        }
        //something enters the first sensor
        else if((proximityVoltage - previousVoltage) <= -Constants.INTAKE_PROXIMITY_DIFFERENCE)
        {
            RobotContainer.ballCount++;
            RobotContainer.getTransport().stop();
        }

        //something enters the second sensor
        if((proximityVoltageTwo - previousVoltageTwo) <= -Constants.INTAKE_PROXIMITY_DIFFERENCE)
        {
            RobotContainer.getTransport().stop();
        }
        else if((proximityVoltageTwo - previousVoltageTwo) >= Constants.INTAKE_PROXIMITY_DIFFERENCE)
        {
            RobotContainer.ballCount--;
            if(RobotContainer.ballCount > 0)
            {
                RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
            }
        }

        previousVoltage = proximityVoltage;
        previousVoltageTwo = proximityVoltageTwo;
    }

}