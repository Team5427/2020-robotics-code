package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestSubsystem extends SubsystemBase
{
    private final SpeedController motor1, motor2; 
    public TestSubsystem(SpeedController motor1, SpeedController motor2)
    {
        this.motor1 = motor1;
        this.motor2 = motor2;
    }

    public void move(double speed)
    {
        motor1.set(speed);
        motor2.set(speed);
    }

    @Override
    public void periodic()
    {

    }
}