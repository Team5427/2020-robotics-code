package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.AnalogInput;

public class Transport extends SubsystemBase
{
    public static double proximityVoltage;
    private AnalogInput proximitySensor;
    public static double proximityVoltageTwo;
    private AnalogInput proximitySensorTwo;
    private SpeedController transportMotor;  
    public static boolean firstSensor = false;
    public static boolean secondSensor = false;
    public static boolean shooterPressed = false;
    private double secondCount = 0;
    private double firstCount = 0;
   
    
    public Transport (SpeedController transportMotor, AnalogInput proximitySensor, AnalogInput proximitySensorTwo) 
    {
        this.proximitySensor =  proximitySensor;
        this.proximitySensorTwo = proximitySensorTwo;
        proximityVoltage = getDistance();
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
        // proximityVoltageTwo = getDistanceTwo();

        //something enters the first sensor
        if(proximityVoltage <= Constants.PROXIMITY_COVERED)
        {
            firstCount++;

            if(firstCount >= 3)
            {
                System.out.println("ENTERED");
                firstSensor = true;
                RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
            }
        }
        else
        {
            firstCount = 0;
        }

        //something leaves the first sensor
        if(firstSensor && proximityVoltage >= Constants.PROXIMITY_UNCOVERED)
        {
            RobotContainer.ballsIn++;
            firstSensor = false;
        }
        if(!shooterPressed && proximityVoltage >= Constants.PROXIMITY_UNCOVERED)
        {
            RobotContainer.getTransport().stop();
        }

        // //something enters the second sensor
        // if(proximityVoltageTwo <= Constants.PROXIMITY_COVERED)
        // {
        //     secondCount++;

        //     if(secondCount >= 3)
        //     {
        //         // if(RobotContainer.getShooter().getShooterMotorBottom().getClosedLoopError() >= Constants.SHOOTER_ERROR_TOLERANCE ||
        //         // RobotContainer.getShooter().getShooterMotorTop().getClosedLoopError() >= Constants.SHOOTER_ERROR_TOLERANCE)
        //         // {
        //         //     RobotContainer.getTransport().stop();
        //         //     RobotContainer.getPulley().stop();
        //         // }
        //         // else
        //         // {
        //             secondSensor = true;
        //             RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
        //             RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
        //             secondCount = 0;
        //         // }
        //     }
        // }
        // else
        // {
        //     secondCount = 0;
        // }

        // if(secondSensor && proximityVoltageTwo >= Constants.PROXIMITY_COVERED && RobotContainer.ballsOut == RobotContainer.ballsIn)
        // {
        //     RobotContainer.getTransport().stop();
        //     secondSensor = false;
        //     shooterPressed = false;
        // }
    }

}