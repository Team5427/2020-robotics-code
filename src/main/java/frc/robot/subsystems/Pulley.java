package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Pulley extends SubsystemBase
{
    private double proximityVoltage, previousVoltage;
    private SpeedController pulleyMotor;
    private AnalogInput pulleyProximity;

    public Pulley(SpeedController pulleyMotor, AnalogInput pulleyProximity)
    {
        this.pulleyMotor = pulleyMotor;
        this.pulleyProximity = pulleyProximity;
        proximityVoltage = previousVoltage = 0;
    }

    public void movePulley(double speed)
    {
        pulleyMotor.set(speed);
    }

    public void stop()
    {
        pulleyMotor.stopMotor();
    }

    public double getDistance()
    {
        double distance = (1/pulleyProximity.getVoltage())*6.1111126 * 1/2.54;
        return distance;
    }

    @Override
    public void periodic()
    {
        // proximityVoltage = getDistance();

        // //something leaves
        // if((proximityVoltage - previousVoltage) >= Constants.INTAKE_PROXIMITY_DIFFERENCE)
        // {
        //     RobotContainer.getPulley().stop();
        //     RobotContainer.loop = 0;
        // }
    }
}