package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Throttle extends SubsystemBase {
 
	
	private SpeedController motorController;


    public Throttle(SpeedController motorController) 
    {
        this.motorController = motorController;
    }

    @Override
    public void periodic() 
    {
        if(RobotContainer.getJoy().getPOV() >= 225 && RobotContainer.getJoy().getPOV() <= 315)
        {
            motorController.set(0.2);
        }
        if(RobotContainer.getJoy().getPOV() >= 45 && RobotContainer.getJoy().getPOV() <= 135)
        {
            motorController.set(-0.2);
        }
        if(RobotContainer.getJoy().getPOV() == -1)
        {
            motorController.set(0);
        }
    }

    public SpeedController getMotor()
    {
        return motorController;
    }
}