package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.IntakeBall;
import edu.wpi.first.wpilibj.AnalogInput;

public class Transport extends SubsystemBase
{
    public static double proximityVoltageIntake;
    private AnalogInput proximitySensor;
    public static double proximityVoltageTwo;
    private AnalogInput proximitySensorTwo;
    private SpeedController transportMotor;  
    public static boolean firstSensor = false;
    public static boolean secondSensor = false;
    public static boolean shooterPressed = false;
   
    
    public Transport (SpeedController transportMotor, AnalogInput proximitySensor, AnalogInput proximitySensorTwo) 
    {
        this.proximitySensor =  proximitySensor;
        this.proximitySensorTwo = proximitySensorTwo;
        proximityVoltageIntake = getDistanceIntake();
        proximityVoltageTwo = getDistanceTwo();
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

    public double getDistanceIntake(){
        
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
        if(RobotContainer.ballCount < 5 && RobotContainer.getTransport().getDistanceIntake() < 2.7)
        {
            new IntakeBall();
            firstSensor = true;
        }
        
    }

}