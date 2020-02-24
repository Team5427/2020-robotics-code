package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase
{
    private SpeedControllerGroup left, right;

    private DifferentialDrive driveBase;

    public DriveTrain(SpeedControllerGroup left, SpeedControllerGroup right, DifferentialDrive driveBase)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
    }

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        //System.out.println(leftSpeed);
        left.set(leftSpeed);
        right.set(-rightSpeed);
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }

    public void takeJoystickInputs(Joystick joy)
    {
        driveBase.arcadeDrive(joy.getY(), -joy.getZ() * Constants.Z_ROT_DAMPENING);
    }

    public double getAvgDistance()
    {
        return (RobotContainer.getEncLeft().getDistance() + RobotContainer.getEncRight().getDistance()) / 2;
    }

    public double getAvgRate()
    {
        return (RobotContainer.getEncLeft().getRate() + RobotContainer.getEncRight().getRate()) / 2;
    }

    @Override
    public void periodic()
    {}
    
}