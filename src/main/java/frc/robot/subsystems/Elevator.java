package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase
{

    private SpeedController left, right;
    private Encoder leftEnc, rightEnc;
    private DigitalInput limit;

    public Elevator(SpeedController left, SpeedController right, Encoder elevatorLeftEnc, Encoder elevatorRightEnc, DigitalInput limit)
    {
        this.left = left;
        this.right = right;
        this.leftEnc = elevatorLeftEnc;
        this.rightEnc = elevatorRightEnc;
        this.limit = limit;
    }

    public void setSpeed(double speed) 
    {
        left.set(speed);
        right.set(speed);
    }

    public void stop() 
    {
        left.stopMotor();
        right.stopMotor();
    }

    public boolean getLimit()
    {
        return limit.get();
    }

    public boolean getEncLimit()
    {
        return leftEnc.getDistance() >= Constants.ELEVATOR_UPPER_LIMIT || rightEnc.getDistance() >= Constants.ELEVATOR_UPPER_LIMIT;
    }
    
}