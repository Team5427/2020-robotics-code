package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pulley extends Subsystem
{
    private SpeedController pulleyMotor;
    private AnalogInput pulleyProximity;

    public Pulley(SpeedController pulleyMotor, AnalogInput pulleyProximity)
    {
        this.pulleyMotor = pulleyMotor;
        this.pulleyProximity = pulleyProximity;
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
    protected void initDefaultCommand() 
    {}

}