package frc.robot.subsystems;

import java.util.Base64.Encoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator<LimitSwitch> extends Subsystem
{

    private SpeedController left, right;
    private Encoder leftEnc, rightEnc;
    //private LimitSwitch topSwitch, bottomSwitch;
    private DigitalInput high, low;

    public void setSpeed(double speed) {
        left.set(speed);
        right.set(speed);
    }

    public void stopMotor() {
        left.stopMotor();
        right.stopMotor();
    }

    public void periodic(boolean high, boolean low) {
        if(high == true)
            stopMotor();
        else if(low == true)
            stopMotor();

        if(Math.abs(leftEnc))

    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

}